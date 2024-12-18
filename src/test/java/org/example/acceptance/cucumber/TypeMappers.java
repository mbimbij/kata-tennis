package org.example.acceptance.cucumber;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.github.wnameless.json.unflattener.JsonUnflattener;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.DocStringType;
import io.cucumber.java.ParameterType;
import lombok.SneakyThrows;
import org.example.shared.core.PlayerScore;
import org.example.shared.core.Score;
import org.example.rest.ScoreDto;

import java.util.Collection;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class TypeMappers {

    private static final ObjectMapper objectMapper = JsonMapper.builder()
            .configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true)
            .configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS, true)
            .configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_VALUES, true)
            .addModules(new JavaTimeModule(), new Jdk8Module())
            .build().setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);

    @SneakyThrows
    public static <T> Collection<T> convertDatatableList(DataTable dataTable, Class<T> clazz) {
        return dataTable.entries().stream()
                .map(map -> convertMapToObject(map, clazz))
                .toList();
    }


    @SneakyThrows
    private static <T> T convertMapToObject(Map<String, String> map, Class<T> clazz) {
        return objectMapper.readValue(JsonUnflattener.unflatten(objectMapper.writeValueAsString(map)),
                clazz);
    }

    @SneakyThrows
    @DocStringType
    public ScoreDto scoreDto(String docString) {
        return objectMapper.readValue(docString, ScoreDto.class);
    }

    @ParameterType(".*")
    public Score score(String scoreString) {
        assertThat(scoreString).matches(".*-.*");
        String[] scoresSplit = scoreString.replace("\"", "").split("-");
        PlayerScore playerAScore = PlayerScore.fromValue(scoresSplit[0].trim());
        PlayerScore playerBScore = PlayerScore.fromValue(scoresSplit[1].trim());
        return Score.fromEnum(playerAScore, playerBScore);
    }
}
