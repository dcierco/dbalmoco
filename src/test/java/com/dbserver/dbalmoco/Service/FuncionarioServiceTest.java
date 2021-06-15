package com.dbserver.dbalmoco.Service;

import static org.mockito.BDDMockito.given;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.aventstack.extentreports.testng.listener.ExtentITestListenerAdapter;
import com.dbserver.dbalmoco.models.Funcionario;
import com.dbserver.dbalmoco.repository.FuncionarioRepository;
import com.dbserver.dbalmoco.service.Funcionario.FuncionarioServiceImpl;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.ITestListener;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;


@ActiveProfiles("test")
@SpringBootTest
@Listeners({ExtentITestListenerAdapter.class})
public class FuncionarioServiceTest extends AbstractTestNGSpringContextTests implements ITestListener {
    
    @InjectMocks
	FuncionarioServiceImpl funcionarioService;

    @Mock
    FuncionarioRepository funcionarioRepository;

    @BeforeMethod
    public void init(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldGetFuncionarioList(){
        Funcionario funcionario1 = new Funcionario();
        Funcionario funcionario2 = new Funcionario();
        List<Funcionario> listFuncionarios = new ArrayList<Funcionario>();
		listFuncionarios.add(funcionario1);
		listFuncionarios.add(funcionario2);
        Iterable<Funcionario> iter = listFuncionarios;
		
		given(funcionarioRepository.findAll()).willReturn(iter);
		Assert.assertEquals(funcionario1,this.funcionarioService.listarFuncionarios().get(0));
		Assert.assertEquals(funcionario2,this.funcionarioService.listarFuncionarios().get(1));

    }

    @Test
	public void shouldGetFuncionario() {
		Funcionario funcionario = new Funcionario();
		given(this.funcionarioRepository.findById(Mockito.any())).willReturn(Optional.of(funcionario));
		Assert.assertEquals(funcionario, funcionarioService.obterFuncionarioPorId(1));
	}

    @Test
    @Transactional
    public void shouldSaveFuncionario(){
        Funcionario funcionario1 = new Funcionario(1, "Funcionario de Teste", null, "teste@teste.com", "teste123", null);
        given(this.funcionarioRepository.save(Mockito.any())).willReturn(funcionario1);
        Assert.assertEquals(funcionario1, this.funcionarioService.salvarFuncionario(funcionario1));
    }

    @Test
    @Transactional
    public void shouldDeleteFuncionario(){
        Funcionario funcionario;
        this.funcionarioService.excluirFuncionario(1);
        try {
        	funcionario = this.funcionarioService.obterFuncionarioPorId(1);
		} catch (Exception e) {
			funcionario = null;
		}
        Assert.assertNull(funcionario);
    }

    @Test
    @Transactional
    public void shouldUpdateFuncionario(){
    	Funcionario funcionario = this.funcionarioService.obterFuncionarioPorId(1);
    	String nomeAntigo = funcionario.getNome();
        String novoNome = nomeAntigo + "X";
        funcionario.setNome(novoNome);
        this.funcionarioService.atualizarFuncionario(funcionario);
        funcionario = this.funcionarioService.obterFuncionarioPorId(1);
        Assert.assertEquals(funcionario.getNome(), novoNome);
    }

}
