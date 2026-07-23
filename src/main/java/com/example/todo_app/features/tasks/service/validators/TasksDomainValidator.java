package com.example.todo_app.features.tasks.service.validators;

import com.example.todo_app.core.domain.*;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import java.util.Set;
import org.springframework.stereotype.Component;

@Component
public class TasksDomainValidator {
  private final Validator validator;

  public TasksDomainValidator(Validator validator) {
    this.validator = validator;
  }

  public void validate(TasksDomain task) {

    Set<ConstraintViolation<TasksDomain>> violations = validator.validate(task);

    if (!violations.isEmpty()) {
      throw new ConstraintViolationException(violations);
    }
  }
}
