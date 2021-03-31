package com.dbserver.dbalmoco.service.Restaurante;

import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.dbserver.dbalmoco.models.Restaurante;
import com.dbserver.dbalmoco.repository.RestauranteRepository;

import org.springframework.stereotype.Service;

@Service
public class RestauranteServiceImpl implements RestauranteService{
    private final RestauranteRepository restauranteRepository;

    public RestauranteServiceImpl(RestauranteRepository restauranteRepository){
        this.restauranteRepository = restauranteRepository;
    }

    @Override
    public List<Restaurante> listarRestaurantes() {
        return StreamSupport.stream(this.restauranteRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    @Override
    public Restaurante obterRestaurantePorId(Integer id) throws NoSuchElementException{
        return this.restauranteRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Não existe no banco de dados restaurante com o id:" + id));
    }

    @Override
    public Restaurante salvarRestaurante(Restaurante restaurante) {
        return restauranteRepository.save(restaurante);
    }

    @Override
    public Restaurante atualizarRestaurante(Restaurante restaurante) {
        this.obterRestaurantePorId(restaurante.getId());
        return restauranteRepository.save(restaurante);
    }

    @Override
    public void excluirRestaurante(Integer id) {
        this.restauranteRepository.deleteById(id);
    }

    private boolean foiVotadoEssaSemana(Restaurante restaurante){
        if(restaurante.getVotado_data() == null){return false;}

        Calendar c = Calendar.getInstance();
        c.setFirstDayOfWeek(Calendar.MONDAY);

        c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);

        Date monday = c.getTime();

        Date nextMonday= new Date(monday.getTime()+7*24*60*60*1000);

        return restaurante.getVotado_data().after(monday) && restaurante.getVotado_data().before(nextMonday);
    }

    @Override
    public List<Restaurante> listaRestaurantesVotaveis() {
        return StreamSupport.stream(this.restauranteRepository.findAll().spliterator(), false).filter(p -> !foiVotadoEssaSemana(p)).collect(Collectors.toList());
    }

    @Override
    public Restaurante obterMaisVotadoDia() {
        return StreamSupport.stream(this.restauranteRepository.findAll().spliterator(), true).filter(p -> !foiVotadoEssaSemana(p)).max(new CompareRestaurantesVotos()).orElseThrow(() -> new NullPointerException("Stream de restaurantes está vazia."));
    }

    private class CompareRestaurantesVotos implements Comparator<Restaurante>{
        @Override
        public int compare(Restaurante o1, Restaurante o2) {
            int a = o1.getVotos().size();
            int b = o2.getVotos().size();
            return Integer.compare(a, b);
        }
    }

    @Override
    public void atualizaMaisVotadoDia() {
        // TODO: Criar médtodo e atualizar entradas.
        
    }

    
}
