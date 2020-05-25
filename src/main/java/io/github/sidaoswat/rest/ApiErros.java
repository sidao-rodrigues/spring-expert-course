package io.github.sidaoswat.rest;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;

public class ApiErros {

    @Getter
    private List<String> erros;

    public ApiErros(List<String> listaMsgErros){
        this.erros = listaMsgErros;
    }

    public ApiErros(String messagemErro){
        this.erros = Arrays.asList(messagemErro);
    }

}
