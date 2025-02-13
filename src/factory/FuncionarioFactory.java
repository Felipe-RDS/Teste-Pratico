package factory;

import model.Funcionario;

import java.math.BigDecimal;
import java.time.LocalDate;

public class FuncionarioFactory {
  public static Funcionario criar(String nome, LocalDate dataNascimento, BigDecimal salario, String funcao) {
    return new Funcionario(nome, dataNascimento, salario, funcao);
  }
}
