package med.voll.api.controller;

import med.voll.api.domain.endereco.DadosEndereco;
import med.voll.api.domain.endereco.Endereco;
import med.voll.api.domain.medico.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
@DataJpaTest
class MedicoControllerTest {
    @Autowired
    private MockMvc mvc;
    @Autowired
    private JacksonTester<DadosCadastroMedico>dadosCadastroMedicoJson;

    @Autowired
    private JacksonTester<DadosDetalhamentoMedico>dadosDetalhamentoMedicoJson;
    @MockBean
    private DadosCadastroMedico dadosCadastroMedico;
    @Autowired
    private MedicoRepository medicoRepository;
    @Autowired
    private TestEntityManager em;


    @Test
    @DisplayName("Deveria devolver codigo http 400 quando informacoes estao invalidas")
    @WithMockUser
    void cadastrar_cenario1()  throws Exception {
        var response = mvc.perform(post("/medicos")).andReturn().getResponse();
        Assertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }
    @Test
    @DisplayName("Deveria devolver codigo http 200 quando informacoes estao validas")
    @WithMockUser
    void cadastrar_cenario2()  throws Exception {

        var especialidade = Especialidade.CARDIOLOGIA;
        var endereco = new DadosEndereco("teste","barana","23123467","parada","br","apt T5","400");

       var medico = cadastrarMedico();

        var response = mvc.perform(post("/medicos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(dadosCadastroMedicoJson.write(
                        new DadosCadastroMedico("carlos beti","carlos_beti@gmail.com","115594737","123255",especialidade,endereco)
                ).getJson())
        ).andReturn().getResponse();
        Assertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        var jsonEsperado = dadosDetalhamentoMedicoJson.write(
                new DadosDetalhamentoMedico(null,"carlos beti","carlos_beti@gmail.com","123255","115594737",especialidade,endereco2)
        ).getJson();


        Assertions.assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);


    }
    private Medico cadastrarMedico(String nome, String email, String crm, Especialidade especialidade) {
        var medico = new Medico(dadosMedico(nome, email, crm, especialidade));
        em.persist(medico);
        return medico;
    }
    private DadosCadastroMedico dadosMedico(String nome, String email,String telefone, String crm, Especialidade especialidade) {
        return new DadosCadastroMedico(
                nome,
                email,
                telefone,
                crm,
                especialidade,
                dadosEndereco()
        );

    }
    private DadosEndereco dadosEndereco() {
        return new DadosEndereco();
    }
}