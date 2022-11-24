package platform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import platform.entity.Code;

import java.util.UUID;

@Repository
public interface CodeRepository extends JpaRepository<Code, UUID> {
    Code findCodeById(UUID id);

}