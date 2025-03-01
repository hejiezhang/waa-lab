package edu.miu.waa_lab.repository;

import edu.miu.waa_lab.entity.Logger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoggerRepo extends JpaRepository<Logger, Long> {
}
