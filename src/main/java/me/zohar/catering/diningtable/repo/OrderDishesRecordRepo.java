package me.zohar.catering.diningtable.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import me.zohar.catering.diningtable.domain.OrderDishesRecord;

public interface OrderDishesRecordRepo
		extends JpaRepository<OrderDishesRecord, String>, JpaSpecificationExecutor<OrderDishesRecord> {

	List<OrderDishesRecord> findByBookDiningTableRecordId(String bookDiningTableRecordId);

}
