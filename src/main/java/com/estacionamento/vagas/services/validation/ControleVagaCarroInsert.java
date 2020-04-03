package com.estacionamento.vagas.services.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = ControleVagaCarroInsertValidator.class) //nome do Validator
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface ControleVagaCarroInsert { //nome anoteção
	String message() default "Erro de validação"; //mensagem padrão

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}