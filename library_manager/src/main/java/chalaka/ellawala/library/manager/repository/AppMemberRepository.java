package chalaka.ellawala.library.manager.repository;

import chalaka.ellawala.library.manager.entity.AppMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppMemberRepository extends JpaRepository<AppMember, Long> {
}
