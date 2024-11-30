package org.example.acceptance.cucumber.utils;

import org.example.rest.ScoreDto;

public class DtoMapper {
    public ScoreDto fromOpenApiDto(org.openapitools.client.model.ScoreDto dto) {
        return new ScoreDto(dto.getA(), dto.getB());
    }

    public org.openapitools.client.model.ScoreDto toOpenApiDto(ScoreDto dto) {
        org.openapitools.client.model.ScoreDto target = new org.openapitools.client.model.ScoreDto();
        target.setA(dto.playerAScore());
        target.setB(dto.playerBScore());
        return target;
    }
}
