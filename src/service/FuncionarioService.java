package service;

import factory.FuncionarioFactory;
import model.Funcionario;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class FuncionarioService {

  public void inserirFuncionarios(List<Funcionario> funcionarios) {
    funcionarios.add(FuncionarioFactory.criar("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador"));
    funcionarios.add(FuncionarioFactory.criar("João", LocalDate.of(1990, 5, 12), new BigDecimal("2284.38"), "Operador"));
    funcionarios.add(FuncionarioFactory.criar("Caio", LocalDate.of(1961, 5, 2), new BigDecimal("9836.14"), "Coordenador"));
    funcionarios.add(FuncionarioFactory.criar("Miguel", LocalDate.of(1988, 10, 14), new BigDecimal("19119.88"), "Diretor"));
    funcionarios.add(FuncionarioFactory.criar("Alice", LocalDate.of(1995, 1, 5), new BigDecimal("2234.68"), "Recepcionista"));
    funcionarios.add(FuncionarioFactory.criar("Heitor", LocalDate.of(1999, 11, 19), new BigDecimal("1582.72"), "Operador"));
    funcionarios.add(FuncionarioFactory.criar("Arthur", LocalDate.of(1993, 3, 31), new BigDecimal("4071.84"), "Contador"));
    funcionarios.add(FuncionarioFactory.criar("Laura", LocalDate.of(1994, 7, 8), new BigDecimal("3017.45"), "Gerente"));
    funcionarios.add(FuncionarioFactory.criar("Heloisa", LocalDate.of(2003, 5, 24), new BigDecimal("1606.85"), "Eletricista"));
    funcionarios.add(FuncionarioFactory.criar("Helena", LocalDate.of(1996, 9, 2), new BigDecimal("2799.93"), "Gerente"));
  }

  public void removerFuncionario(List<Funcionario> funcionarios, String nome) {
    funcionarios.removeIf(f -> f.getNome().equals(nome));
  }

  public void imprimirFuncionarios(List<Funcionario> funcionarios) {
    System.out.println("**Lista de Funcionários:**");
    funcionarios.forEach(f -> System.out.println(formatarFuncionario(f)));
  }

  public void aumentarSalario(List<Funcionario> funcionarios, BigDecimal percentual) {
    funcionarios.forEach(f -> f.aumentarSalario(percentual));
  }

  public Map<String, List<Funcionario>> agruparPorFuncao(List<Funcionario> funcionarios) {
    return funcionarios.stream()
      .collect(Collectors.groupingBy(Funcionario::getFuncao));
  }

  public void imprimirFuncionariosAgrupados(Map<String, List<Funcionario>> funcionariosPorFuncao) {
    System.out.println("\n**Funcionários Agrupados por Função:**");
    funcionariosPorFuncao.forEach((funcao, lista) -> {
      System.out.println("\n**" + funcao + "**");
      lista.forEach(f -> System.out.println(formatarFuncionario(f)));
    });
  }

  public void imprimirAniversariantesMes(List<Funcionario> funcionarios, int... meses) {
    System.out.println("\n**Funcionários que fazem aniversário nos meses especificados:**");
    List<Integer> listaMeses = Arrays.stream(meses).boxed().toList();
    funcionarios.stream()
      .filter(f -> listaMeses.contains(f.getDataNascimento().getMonthValue()))
      .forEach(f -> System.out.println(formatarFuncionario(f)));
  }

  public void imprimirFuncionarioMaiorIdade(List<Funcionario> funcionarios) {
    System.out.println("\n**Funcionário com Maior Idade:**");
    funcionarios.stream()
      .min(Comparator.comparing(Funcionario::getDataNascimento))
      .ifPresent(f -> System.out.println("Nome: " + f.getNome() + ", Idade: " + calcularIdade(f.getDataNascimento())));
  }

  public void imprimirFuncionariosOrdemAlfabetica(List<Funcionario> funcionarios) {
    System.out.println("\n**Funcionários em Ordem Alfabética:**");
    funcionarios.stream()
      .sorted(Comparator.comparing(Funcionario::getNome))
      .forEach(f -> System.out.println(formatarFuncionario(f)));
  }

  public void imprimirTotalSalarios(List<Funcionario> funcionarios) {
    System.out.println("\n**Total dos Salários:**");
    BigDecimal total = BigDecimal.ZERO;
    for (Funcionario funcionario : funcionarios) {
      total = total.add(funcionario.getSalario());
    }
    String totalFormatado = String.format("%,.2f", total);
    System.out.println("R$ "+ totalFormatado);
  }

  public void imprimirSalariosMinimos(List<Funcionario> funcionarios, BigDecimal salarioMinimo) {
    System.out.println("\n**Salários em Relação ao Salário Mínimo:**");
    funcionarios.forEach(f -> {
      BigDecimal salariosMinimos = f.getSalario().divide(salarioMinimo, 2, RoundingMode.HALF_UP);
      System.out.println(f.getNome() + " ganha " + salariosMinimos + " salários mínimos.");
    });
  }

  private String formatarFuncionario(Funcionario funcionario) {
    return "Nome: " + funcionario.getNome() + ", Data de Nascimento: " + funcionario.getDataNascimento()
      .format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy")) + ", Salário: R$ " +
      String.format("%,.2f", funcionario.getSalario()) + ", Função: " + funcionario.getFuncao();
  }

  private int calcularIdade(LocalDate dataNascimento) {
    return LocalDate.now().getYear() - dataNascimento.getYear();
  }

}
