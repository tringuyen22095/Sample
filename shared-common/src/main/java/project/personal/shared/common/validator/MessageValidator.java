package project.personal.shared.common.validator;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import project.personal.shared.common.constant.MessageType;
import project.personal.shared.common.model.req.MessageReq;
import project.personal.shared.common.validation.MessageValidation;

public class MessageValidator implements ConstraintValidator<MessageValidation, MessageReq> {

	List<String> valueList = null;

	@Override
	public void initialize(MessageValidation constraintAnnotation) {
		valueList = new ArrayList<String>();

		for (MessageType enumVal : MessageType.values()) {
			valueList.add(enumVal.getValue());
		}
	}

	@Override
	public boolean isValid(MessageReq req, ConstraintValidatorContext context) {
		context.disableDefaultConstraintViolation();
		if (CollectionUtils.isEmpty(req.getDocuments()) && StringUtils.isBlank(req.getContent())) {
			context.buildConstraintViolationWithTemplate("Both content & document can't not be blank.").addConstraintViolation();
			return false;
		} else if (valueList.contains(req.getType())) {
			if (MessageType.TEXT.equals(MessageType.valueOf(req.getType())) && StringUtils.isBlank(req.getContent())) {
				context.buildConstraintViolationWithTemplate("Content can't not be blank.").addConstraintViolation();
				return false;
			} else if (!MessageType.TEXT.equals(MessageType.valueOf(req.getType())) && CollectionUtils.isEmpty(req.getDocuments())) {
				context.buildConstraintViolationWithTemplate("Document can't not be empty.").addConstraintViolation();
				return false;
			}
		}
		return true;
	}

}