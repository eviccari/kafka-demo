package com.viccari.learning.kafka_demo.models;

import com.viccari.learning.kafka_demo.exceptions.UnprocessableEntityException;
import com.viccari.learning.kafka_demo.services.StringValidationService;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
public class BusinessEntity implements Serializable, EntityValidatable<BusinessEntity> {

    private static final long serialVersionUID = -6662954551983436723L;
    private String id;
    private String description;
    private String version;
    private String receivedAt;
    private Boolean withInternalServerError;
    public static final String BUSINESS_TOPIC = "business-topic";

    public BusinessEntity() {
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }

    @Override
    public void validate(BusinessEntity entity) throws UnprocessableEntityException {

        if(entity == null){
            throw new UnprocessableEntityException("entity_is_required");
        }

        if(StringValidationService.isEmpty(entity.getDescription())){
            throw new UnprocessableEntityException("description_is_required");
        }

        if(StringValidationService.isEmpty(entity.getId())){
            throw new UnprocessableEntityException("id_is_required");
        }
    }

}
