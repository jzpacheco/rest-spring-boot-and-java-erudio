package br.com.jzpacheco.restspringbootandjavaerudio.integrationtests.controller.withyaml.withxml.mapper;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import io.restassured.mapper.ObjectMapper;
import io.restassured.mapper.ObjectMapperDeserializationContext;
import io.restassured.mapper.ObjectMapperSerializationContext;
//import org.testcontainers.shaded.com.fasterxml.jackson.core.JsonProcessingException;
//import org.testcontainers.shaded.com.fasterxml.jackson.databind.JsonMappingException;
//import org.testcontainers.shaded.com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.JsonMappingException;
import java.util.logging.Logger;

public class YMLMapper implements ObjectMapper {

    private Logger logger = Logger.getLogger(YMLMapper.class.getName());

    private com.fasterxml.jackson.databind.ObjectMapper objectMapper;

    protected TypeFactory typeFactory;

    public YMLMapper() {
        this.objectMapper = new com.fasterxml.jackson.databind.ObjectMapper(new YAMLFactory());
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        this.typeFactory = TypeFactory.defaultInstance();
    }
    @SuppressWarnings("rawtypes")
    @Override
    public Object deserialize(ObjectMapperDeserializationContext context) {
        try{
            String dataToDeserialize = context.getDataToDeserialize().asString();
            Class type = (Class) context.getType();

            logger.info("Trying desrialize objecto of type "+ type);

            return objectMapper.readValue(dataToDeserialize, typeFactory.constructType(type));

        } catch (JsonMappingException e){
            logger.severe("Error deserializing object");
            e.printStackTrace();
        } catch (JsonProcessingException e){
            logger.severe("Error deserializing object");
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Object serialize(ObjectMapperSerializationContext objectMapperSerializationContext) {
        return null;
    }
}
