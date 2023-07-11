package br.com.lamppit.teste.dto;


import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class LoginRequestDto {
    @NotNull
    @NotEmpty
    @ApiModelProperty(example = "admin")
    private String username;

    @NotNull
    @NotEmpty
    @ApiModelProperty(example = "123456")
    private String password;
}