package flutter.task;

import org.springframework.data.jpa.repository.JpaRepository;

public interface taskRepo extends JpaRepository<Task,Long> {
}