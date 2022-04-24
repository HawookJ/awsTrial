package flutter.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/tasks")
public class taskController {

    @Autowired
    private taskRepo taskRepo;

    @GetMapping
    public List<Task> getTasks(){
        return taskRepo.findAll();
    }

    @PostMapping("/add")
    public Task addTask(@Valid @RequestBody Task task){
        return taskRepo.save(task);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateTask(@PathVariable Long id){
        boolean exist = taskRepo.existsById(id);
        if(exist){
        	Task task = taskRepo.getById(id);
            boolean done = task.isDone();
            task.setDone(!done);
            taskRepo.save(task);
            return new ResponseEntity<>("Task is updated", HttpStatus.OK);
        }
        return new ResponseEntity<>("Task is not exist", HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteTask(@PathVariable Long id){
        boolean exist = taskRepo.existsById(id);
        if(exist){
            taskRepo.deleteById(id);
            return new ResponseEntity<>("Task is deleted", HttpStatus.OK);
        }
        return new ResponseEntity<>("Task is not exist", HttpStatus.BAD_REQUEST);
    }
}