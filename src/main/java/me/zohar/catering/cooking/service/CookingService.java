package me.zohar.catering.cooking.service;

import java.util.ArrayList;
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
import me.zohar.catering.common.valid.ParamValid;
import me.zohar.catering.common.vo.PageResult;
import me.zohar.catering.constants.Constant;
import me.zohar.catering.cooking.domain.Cooking;
import me.zohar.catering.cooking.domain.StyleOfCooking;
import me.zohar.catering.cooking.param.CookingParam;
import me.zohar.catering.cooking.param.CookingQueryCondParam;
import me.zohar.catering.cooking.param.StyleOfCookingParam;
import me.zohar.catering.cooking.param.StyleOfCookingQueryCondParam;
import me.zohar.catering.cooking.repo.CookingRepo;
import me.zohar.catering.cooking.repo.StyleOfCookingRepo;
import me.zohar.catering.cooking.vo.CookingVO;
import me.zohar.catering.cooking.vo.StyleOfCookingVO;

@Validated
@Service
public class CookingService {

	@Autowired
	private CookingRepo cookingRepo;

	@Autowired
	private StyleOfCookingRepo styleOfCookingRepo;

	@Transactional(readOnly = true)
	public List<CookingVO> findCookingByStyleOfCookingId(String styleOfCookingId) {
		return CookingVO.convertFor(cookingRepo.findByStyleOfCookingIdAndAvailableFlagIsTrue(styleOfCookingId));
	}

	@Transactional(readOnly = true)
	public List<StyleOfCookingVO> findAllStyleOfCooking() {
		return StyleOfCookingVO.convertFor(styleOfCookingRepo.findByAvailableFlagIsTrueOrderByCreateTimeDesc());
	}

	@Transactional
	public void delStyleOfCookingById(@NotBlank String id) {
		StyleOfCooking po = styleOfCookingRepo.getOne(id);
		po.setAvailableFlag(Constant.逻辑删除标记_已删除);
		styleOfCookingRepo.save(po);
	}

	@Transactional(readOnly = true)
	public StyleOfCookingVO findStyleOfCookingById(@NotBlank String id) {
		return StyleOfCookingVO.convertFor(styleOfCookingRepo.getOne(id));
	}

	@ParamValid
	@Transactional
	public void addOrUpdateStyleOfCooking(StyleOfCookingParam param) {
		// 新增
		if (StrUtil.isBlank(param.getId())) {
			StyleOfCooking po = param.convertToPo();
			styleOfCookingRepo.save(po);
		}
		// 修改
		else {
			StyleOfCooking po = styleOfCookingRepo.getOne(param.getId());
			BeanUtils.copyProperties(param, po);
			styleOfCookingRepo.save(po);
		}
	}

	@Transactional(readOnly = true)
	public PageResult<StyleOfCookingVO> findStyleOfCookingByPage(StyleOfCookingQueryCondParam param) {
		Specification<StyleOfCooking> spec = new Specification<StyleOfCooking>() {

			public Predicate toPredicate(Root<StyleOfCooking> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				List<Predicate> predicates = new ArrayList<Predicate>();
				predicates.add(builder.isTrue(root.get("availableFlag")));
				if (StrUtil.isNotBlank(param.getStyleOfCookingName())) {
					predicates.add(
							builder.like(root.get("styleOfCookingName"), "%" + param.getStyleOfCookingName() + "%"));
				}
				return predicates.size() > 0 ? builder.and(predicates.toArray(new Predicate[predicates.size()])) : null;
			}
		};
		Page<StyleOfCooking> result = styleOfCookingRepo.findAll(spec,
				new PageRequest(param.getPageNum() - 1, param.getPageSize(), Sort.Direction.ASC, "orderNo"));
		PageResult<StyleOfCookingVO> pageResult = new PageResult<>(StyleOfCookingVO.convertFor(result.getContent()),
				param.getPageNum(), param.getPageSize(), result.getTotalElements());
		return pageResult;
	}

	@Transactional
	public void delCookingById(@NotBlank String id) {
		Cooking po = cookingRepo.getOne(id);
		po.setAvailableFlag(Constant.逻辑删除标记_已删除);
		cookingRepo.save(po);
	}

	@Transactional(readOnly = true)
	public CookingVO findCookingById(@NotBlank String id) {
		return CookingVO.convertFor(cookingRepo.getOne(id));
	}

	@ParamValid
	@Transactional
	public void addOrUpdateCooking(CookingParam param) {
		// 新增
		if (StrUtil.isBlank(param.getId())) {
			Cooking po = param.convertToPo();
			cookingRepo.save(po);
		}
		// 修改
		else {
			Cooking po = cookingRepo.getOne(param.getId());
			BeanUtils.copyProperties(param, po);
			cookingRepo.save(po);
		}
	}

	@Transactional(readOnly = true)
	public PageResult<CookingVO> findCookingByPage(CookingQueryCondParam param) {
		Specification<Cooking> spec = new Specification<Cooking>() {

			public Predicate toPredicate(Root<Cooking> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				List<Predicate> predicates = new ArrayList<Predicate>();
				predicates.add(builder.isTrue(root.get("availableFlag")));
				if (StrUtil.isNotBlank(param.getStyleOfCookingId())) {
					predicates.add(builder.equal(root.get("styleOfCookingId"), param.getStyleOfCookingId()));
				}
				if (StrUtil.isNotBlank(param.getCookingName())) {
					predicates.add(builder.like(root.get("cookingName"), "%" + param.getCookingName() + "%"));
				}
				return predicates.size() > 0 ? builder.and(predicates.toArray(new Predicate[predicates.size()])) : null;
			}
		};
		Page<Cooking> result = cookingRepo.findAll(spec,
				new PageRequest(param.getPageNum() - 1, param.getPageSize(), Sort.Direction.ASC, "createTime"));
		PageResult<CookingVO> pageResult = new PageResult<>(CookingVO.convertFor(result.getContent()),
				param.getPageNum(), param.getPageSize(), result.getTotalElements());
		return pageResult;
	}

}
