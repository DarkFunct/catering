package me.zohar.catering.useraccount.service;

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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import cn.hutool.core.util.StrUtil;
import me.zohar.catering.common.exception.BizError;
import me.zohar.catering.common.exception.BizException;
import me.zohar.catering.common.valid.ParamValid;
import me.zohar.catering.common.vo.PageResult;
import me.zohar.catering.useraccount.domain.UserAccount;
import me.zohar.catering.useraccount.param.AddUserAccountParam;
import me.zohar.catering.useraccount.param.ModifyLoginPwdParam;
import me.zohar.catering.useraccount.param.UserAccountEditParam;
import me.zohar.catering.useraccount.param.UserAccountQueryCondParam;
import me.zohar.catering.useraccount.repo.UserAccountRepo;
import me.zohar.catering.useraccount.vo.LoginAccountInfoVO;
import me.zohar.catering.useraccount.vo.UserAccountDetailsInfoVO;
import me.zohar.catering.useraccount.vo.UserAccountInfoVO;

@Validated
@Service
public class UserAccountService {

	@Autowired
	private UserAccountRepo userAccountRepo;

	/**
	 * 更新最近登录时间
	 */
	@Transactional
	public void updateLatelyLoginTime(String userAccountId) {
		UserAccount userAccount = userAccountRepo.getOne(userAccountId);
		userAccount.setLatelyLoginTime(new Date());
		userAccountRepo.save(userAccount);
	}

	@ParamValid
	@Transactional
	public void updateUserAccount(UserAccountEditParam param) {
		UserAccount existUserAccount = userAccountRepo.findByUserName(param.getUserName());
		if (existUserAccount != null && !existUserAccount.getId().equals(param.getUserAccountId())) {
			throw new BizException(BizError.用户名已存在);
		}

		UserAccount userAccount = userAccountRepo.getOne(param.getUserAccountId());
		BeanUtils.copyProperties(param, userAccount);
		userAccountRepo.save(userAccount);
	}

	@Transactional(readOnly = true)
	public UserAccountDetailsInfoVO findUserAccountDetailsInfoById(String userAccountId) {
		UserAccount userAccount = userAccountRepo.getOne(userAccountId);
		return UserAccountDetailsInfoVO.convertFor(userAccount);
	}

	@Transactional(readOnly = true)
	public PageResult<UserAccountDetailsInfoVO> findUserAccountDetailsInfoByPage(UserAccountQueryCondParam param) {
		Specification<UserAccount> spec = new Specification<UserAccount>() {

			public Predicate toPredicate(Root<UserAccount> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				List<Predicate> predicates = new ArrayList<Predicate>();
				if (StrUtil.isNotEmpty(param.getUserName())) {
					predicates.add(builder.like(root.get("userName"), "%" + param.getUserName() + "%"));
				}
				if (StrUtil.isNotEmpty(param.getRealName())) {
					predicates.add(builder.like(root.get("realName"), "%" + param.getRealName() + "%"));
				}
				return predicates.size() > 0 ? builder.and(predicates.toArray(new Predicate[predicates.size()])) : null;
			}
		};

		Page<UserAccount> result = userAccountRepo.findAll(spec,
				new PageRequest(param.getPageNum() - 1, param.getPageSize(), Sort.Direction.DESC, "registeredTime"));
		PageResult<UserAccountDetailsInfoVO> pageResult = new PageResult<>(
				UserAccountDetailsInfoVO.convertFor(result.getContent()), param.getPageNum(), param.getPageSize(),
				result.getTotalElements());
		return pageResult;
	}

	@ParamValid
	@Transactional
	public void modifyLoginPwd(ModifyLoginPwdParam param) {
		UserAccount userAccount = userAccountRepo.getOne(param.getUserAccountId());
		BCryptPasswordEncoder pwdEncoder = new BCryptPasswordEncoder();
		if (!pwdEncoder.matches(param.getOldLoginPwd(), userAccount.getLoginPwd())) {
			throw new BizException(BizError.旧的登录密码不正确);
		}
		modifyLoginPwd(param.getUserAccountId(), param.getNewLoginPwd());
	}

	@Transactional
	public void modifyLoginPwd(@NotBlank String userAccountId, @NotBlank String newLoginPwd) {
		UserAccount userAccount = userAccountRepo.getOne(userAccountId);
		userAccount.setLoginPwd(new BCryptPasswordEncoder().encode(newLoginPwd));
		userAccountRepo.save(userAccount);
	}

	@Transactional(readOnly = true)
	public LoginAccountInfoVO getLoginAccountInfo(String userName) {
		return LoginAccountInfoVO.convertFor(userAccountRepo.findByUserName(userName));
	}

	@Transactional(readOnly = true)
	public UserAccountInfoVO getUserAccountInfo(String userAccountId) {
		return UserAccountInfoVO.convertFor(userAccountRepo.getOne(userAccountId));
	}

	@ParamValid
	@Transactional
	public void addUserAccount(AddUserAccountParam param) {
		UserAccount userAccount = userAccountRepo.findByUserName(param.getUserName());
		if (userAccount != null) {
			throw new BizException(BizError.用户名已存在);
		}
		String encodePwd = new BCryptPasswordEncoder().encode(param.getLoginPwd());
		param.setLoginPwd(encodePwd);
		UserAccount newUserAccount = param.convertToPo();
		userAccountRepo.save(newUserAccount);
	}

	@Transactional
	public void delUserAccount(@NotBlank String userAccountId) {
		userAccountRepo.delete(userAccountId);
	}

}
