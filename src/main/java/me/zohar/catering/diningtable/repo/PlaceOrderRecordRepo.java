package me.zohar.catering.diningtable.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import me.zohar.catering.diningtable.domain.PlaceOrderRecord;

public interface PlaceOrderRecordRepo  extends JpaRepository<PlaceOrderRecord, String>, JpaSpecificationExecutor<PlaceOrderRecord> {

}
