package me.zohar.catering.diningtable.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import cn.hutool.core.util.StrUtil;
import me.zohar.catering.common.exception.BizError;
import me.zohar.catering.common.exception.BizException;
import me.zohar.catering.common.valid.ParamValid;
import me.zohar.catering.common.vo.PageResult;
import me.zohar.catering.constants.Constant;
import me.zohar.catering.diningtable.domain.DiningTable;
import me.zohar.catering.diningtable.domain.OrderDishesRecord;
import me.zohar.catering.diningtable.domain.PlaceOrderRecord;
import me.zohar.catering.diningtable.enums.DiningTableState;
import me.zohar.catering.diningtable.enums.OrderDishesRecordState;
import me.zohar.catering.diningtable.enums.PlaceOrderRecordState;
import me.zohar.catering.diningtable.param.DiningTableParam;
import me.zohar.catering.diningtable.param.DiningTableQueryCondParam;
import me.zohar.catering.diningtable.param.PlaceOrderParam;
import me.zohar.catering.diningtable.param.PlaceOrderRecordQueryCondParam;
import me.zohar.catering.diningtable.repo.DiningTableRepo;
import me.zohar.catering.diningtable.repo.OrderDishesRecordRepo;
import me.zohar.catering.diningtable.repo.PlaceOrderRecordRepo;
import me.zohar.catering.diningtable.vo.DiningTableVO;
import me.zohar.catering.diningtable.vo.OrderDishesRecordVO;
import me.zohar.catering.diningtable.vo.PlaceOrderRecordVO;

@Validated
@Service
public class DiningTableService {

	@Autowired
	private DiningTableRepo diningTableRepo;

	@Autowired
	private PlaceOrderRecordRepo placeOrderRecordRepo;

	@Autowired
	private OrderDishesRecordRepo orderDishesRecordRepo;

	/**
	 * 通知结账
	 * 
	 * @param id
	 */
	@Transactional
	public void noticeSettleAccount(String id) {
		PlaceOrderRecord placeOrderRecord = placeOrderRecordRepo.getOne(id);
		placeOrderRecord.setState(PlaceOrderRecordState.通知结账.getCode());
	}

	@Transactional
	public void settleAccount(String id) {
		Double consume = 0d;
		PlaceOrderRecord placeOrderRecord = placeOrderRecordRepo.getOne(id);
		List<OrderDishesRecord> orderDishesRecords = orderDishesRecordRepo.findByBookDiningTableRecordId(id);
		for (OrderDishesRecord orderDishesRecord : orderDishesRecords) {
			if (orderDishesRecord.getState().equals(OrderDishesRecordState.已上菜.getCode())) {
				consume += orderDishesRecord.getCooking().getPrice();
			}
		}
		placeOrderRecord.setConsume(consume);
		placeOrderRecord.setSettleAccountTime(new Date());
		placeOrderRecord.setState(PlaceOrderRecordState.已结账.getCode());
		withdrawTheTable(placeOrderRecord.getDiningTableId());
	}

	@Transactional
	public void updateOrderDishesRecordState(String id, String state) {
		OrderDishesRecord orderDishesRecord = orderDishesRecordRepo.getOne(id);
		orderDishesRecord.setState(state);
		orderDishesRecordRepo.save(orderDishesRecord);
	}

	@Transactional(readOnly = true)
	public PlaceOrderRecordVO findPlaceOrderRecordById(String id) {
		return PlaceOrderRecordVO.convertFor(placeOrderRecordRepo.getOne(id));
	}

	@Transactional(readOnly = true)
	public PageResult<PlaceOrderRecordVO> findPlaceOrderRecordByPage(PlaceOrderRecordQueryCondParam param) {
		Specification<PlaceOrderRecord> spec = new Specification<PlaceOrderRecord>() {

			public Predicate toPredicate(Root<PlaceOrderRecord> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				List<Predicate> predicates = new ArrayList<Predicate>();
				predicates.add(builder.isTrue(root.get("availableFlag")));
				if (StrUtil.isNotBlank(param.getState())) {
					predicates.add(builder.equal(root.get("state"), param.getState()));
				}
				return predicates.size() > 0 ? builder.and(predicates.toArray(new Predicate[predicates.size()])) : null;
			}
		};
		
		Page<PlaceOrderRecord> result = placeOrderRecordRepo.findAll(spec,
				new PageRequest(param.getPageNum() - 1, param.getPageSize(), Sort.Direction.ASC, "bookTime"));
		PageResult<PlaceOrderRecordVO> pageResult = new PageResult<>(PlaceOrderRecordVO.convertFor(result.getContent()),
				param.getPageNum(), param.getPageSize(), result.getTotalElements());
		return pageResult;
	}

	@Transactional
	public void withdrawTheTable(String tableId) {
		DiningTable table = diningTableRepo.getOne(tableId);
		table.setState(DiningTableState.餐桌状态_空闲.getCode());
	}

	@Transactional(readOnly = true)
	public List<OrderDishesRecordVO> findOrderDishesRecordByBookingId(String bookingId) {
		return OrderDishesRecordVO.convertFor(orderDishesRecordRepo.findByBookDiningTableRecordId(bookingId));
	}

	@Transactional(readOnly = true)
	public List<DiningTableVO> findAllFreeDiningTable() {
		List<DiningTable> diningTables = diningTableRepo
				.findByStateAndAvailableFlagIsTrue(DiningTableState.餐桌状态_空闲.getCode());
		return DiningTableVO.convertFor(diningTables);
	}

	/**
	 * 订位
	 * 
	 * @param id
	 * @return
	 */
	@Transactional
	public PlaceOrderRecordVO booking(@NotBlank String id) {
		DiningTable po = diningTableRepo.getOne(id);
		if (DiningTableState.餐桌状态_已预定.getCode().equals(po.getState())) {
			throw new BizException(BizError.餐桌已被预订);
		}
		po.setState(DiningTableState.餐桌状态_已预定.getCode());
		diningTableRepo.save(po);
		PlaceOrderRecord placeOrderRecord = PlaceOrderRecord.build(po);
		placeOrderRecordRepo.save(placeOrderRecord);
		return PlaceOrderRecordVO.convertFor(placeOrderRecord);
	}

	/**
	 * 点菜
	 * 
	 * @param param
	 */
	@Transactional
	public void orderDishes(PlaceOrderParam param) {
		for (String cookingId : param.getCookingIds()) {
			OrderDishesRecord po = OrderDishesRecord.placeOrder(cookingId, param.getBookDiningTableRecordId());
			orderDishesRecordRepo.save(po);
		}
		PlaceOrderRecord placeOrderRecord = placeOrderRecordRepo.getOne(param.getBookDiningTableRecordId());
		placeOrderRecord.setState(PlaceOrderRecordState.已点菜.getCode());
		placeOrderRecordRepo.save(placeOrderRecord);

	}

	@Transactional
	public void delById(@NotBlank String id) {
		DiningTable po = diningTableRepo.getOne(id);
		po.setAvailableFlag(Constant.逻辑删除标记_已删除);
		diningTableRepo.save(po);
	}

	@Transactional(readOnly = true)
	public DiningTableVO findById(@NotBlank String id) {
		return DiningTableVO.convertFor(diningTableRepo.getOne(id));
	}

	@ParamValid
	@Transactional
	public void addOrUpdateDiningTable(DiningTableParam param) {
		// 新增
		if (StrUtil.isBlank(param.getId())) {
			DiningTable po = param.convertToPo();
			diningTableRepo.save(po);
		}
		// 修改
		else {
			DiningTable po = diningTableRepo.getOne(param.getId());
			BeanUtils.copyProperties(param, po);
			diningTableRepo.save(po);
		}
	}

	@Transactional(readOnly = true)
	public PageResult<DiningTableVO> findDiningTableByPage(DiningTableQueryCondParam param) {
		Specification<DiningTable> spec = new Specification<DiningTable>() {

			public Predicate toPredicate(Root<DiningTable> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				List<Predicate> predicates = new ArrayList<Predicate>();
				predicates.add(builder.isTrue(root.get("availableFlag")));
				if (StrUtil.isNotBlank(param.getDiningTableName())) {
					predicates.add(builder.like(root.get("diningTableName"), "%" + param.getDiningTableName() + "%"));
				}
				return predicates.size() > 0 ? builder.and(predicates.toArray(new Predicate[predicates.size()])) : null;
			}
		};

		Page<DiningTable> result = diningTableRepo.findAll(spec,
				new PageRequest(param.getPageNum() - 1, param.getPageSize(), Sort.Direction.ASC, "orderNo"));
		PageResult<DiningTableVO> pageResult = new PageResult<>(DiningTableVO.convertFor(result.getContent()),
				param.getPageNum(), param.getPageSize(), result.getTotalElements());
		return pageResult;
	}

}
