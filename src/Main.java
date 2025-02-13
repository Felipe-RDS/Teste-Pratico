import model.Funcionario;
import service.FuncionarioService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {
  public static void main(String[] args) {
    List<Funcionario> funcionarios = new ArrayList<>();
    FuncionarioService service = new FuncionarioService();

    // 3.1 - Inserir todos os funcionários
    service.inserirFuncionarios(funcionarios);

    // 3.2 - Remover o funcionário “João” da lista
    service.removerFuncionario(funcionarios, "João");

    // 3.3 - Imprimir todos os funcionários com todas suas informações
    service.imprimirFuncionarios(funcionarios);

    // 3.4 - Os funcionários receberam 10% de aumento de salário
    service.aumentarSalario(funcionarios, new BigDecimal("0.10"));

    // 3.5 - Agrupar os funcionários por função em um MAP
    Map<String, List<Funcionario>> funcionariosPorFuncao = service.agruparPorFuncao(funcionarios);

    // 3.6 - Imprimir os funcionários, agrupados por função
    service.imprimirFuncionariosAgrupados(funcionariosPorFuncao);

    // 3.8 - Imprimir os funcionários que fazem aniversário no mês 10 e 12
    service.imprimirAniversariantesMes(funcionarios, 10, 12);

    // 3.9 - Imprimir o funcionário com a maior idade
    service.imprimirFuncionarioMaiorIdade(funcionarios);

    // 3.10 - Imprimir a lista de funcionários por ordem alfabética
    service.imprimirFuncionariosOrdemAlfabetica(funcionarios);

    // 3.11 - Imprimir o total dos salários dos funcionários
    service.imprimirTotalSalarios(funcionarios);

    // 3.12 - Imprimir quantos salários mínimos ganha cada funcionário
    service.imprimirSalariosMinimos(funcionarios, new BigDecimal("1212.00"));
  }

}