package com.dbserver.dbalmoco.Service;

import static org.mockito.BDDMockito.given;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.aventstack.extentreports.testng.listener.ExtentITestListenerAdapter;
import com.dbserver.dbalmoco.models.Funcionario;
import com.dbserver.dbalmoco.models.Restaurante;
import com.dbserver.dbalmoco.models.Voto;
import com.dbserver.dbalmoco.repository.VotoRepository;
import com.dbserver.dbalmoco.service.Voto.VotoServiceImpl;

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
public class VotoServiceTest extends AbstractTestNGSpringContextTests implements ITestListener {
    
    @InjectMocks
	VotoServiceImpl VotoService;

    @Mock
    VotoRepository VotoRepository;

    @BeforeMethod
    public void init(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldGetVotoList(){
        Voto Voto1 = new Voto();
        Voto Voto2 = new Voto();
        List<Voto> listVotos = new ArrayList<Voto>();
		listVotos.add(Voto1);
		listVotos.add(Voto2);
        Iterable<Voto> iter = listVotos;
		
		given(VotoRepository.findAll()).willReturn(iter);
		Assert.assertEquals(Voto1,this.VotoService.listarVotos().get(0));
		Assert.assertEquals(Voto2,this.VotoService.listarVotos().get(1));

    }

    @Test
	public void shouldGetVoto() {
		Voto Voto = new Voto();
		given(this.VotoRepository.findById(Mockito.any())).willReturn(Optional.of(Voto));
		Assert.assertEquals(Voto, VotoService.obterVotoPorId(1));
	}

    @Test
    @Transactional
    public void shouldSaveVoto(){
        int tamanhoAntigo = this.VotoService.listarVotos().size();
        Voto Voto1 = new Voto();
        Voto1.setData(Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        Voto1.setFuncionario(new Funcionario());
        Voto1.setRestaurante(new Restaurante());
        Assert.assertEquals(Voto1, this.VotoService.salvarVoto(Voto1));
        Assert.assertEquals(tamanhoAntigo, this.VotoService.listarVotos().size()+1);
    }

    @Test
    @Transactional
    public void shouldDeleteVoto(){
        Voto Voto;
        this.VotoService.excluirVoto(1);
        try {
        	Voto = this.VotoService.obterVotoPorId(1);
		} catch (Exception e) {
			Voto = null;
		}
        Assert.assertNull(Voto);
    }

    @Test
    @Transactional
    public void shouldUpdateVoto(){
    	Voto Voto = this.VotoService.obterVotoPorId(1);
        Date novaData = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
        Voto.setData(novaData);
        this.VotoService.atualizarVoto(Voto);
        Voto = this.VotoService.obterVotoPorId(1);
        Assert.assertEquals(Voto.getData(), novaData);
    }

    @Test
    @Transactional
    public void shouldVotar(){
        
    }

    @Test
    @Transactional
    public void shouldNotVotar() {
        
    }

}
