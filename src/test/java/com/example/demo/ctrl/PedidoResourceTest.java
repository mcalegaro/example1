package com.example.demo.ctrl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.apache.logging.log4j.util.Timer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.example.demo.vo.PedidoListarDto;
import com.example.demo.vo.PedidoVO;
import com.example.demo.vo.VencimentoPedidoDto;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class PedidoResourceTest {

	@LocalServerPort
	private int randomServerPort;

	@Autowired
	private PedidoResource ctrl;

	@Test
	void testConsultar() {
		assertEquals(HttpStatus.OK, ctrl.consultar("a").getStatusCode());
	}

	@Test
	void testListar() throws Exception {
		// assertEquals(HttpStatus.OK, ctrl.listar(new PedidoListarDto("aaa",
		// "bbb")).getStatusCode());
		RestTemplate t = new RestTemplate();
		String dto = "{\"a\":\"aaa\",\"b\":\"bbb\"}";
		@SuppressWarnings("rawtypes")
		ResponseEntity<ServicePageableResponse> entity = t.getForEntity(
				"http://localhost:" + randomServerPort + "/pedidos?pedidoListarDto={dto}",
				ServicePageableResponse.class, dto);
		assertEquals(HttpStatus.OK, entity.getStatusCode());
	}

	@Test
	void testCriar() {
		assertEquals(HttpStatus.OK, ctrl.criar(new PedidoVO("a")).getStatusCode());
	}

	@Test
	void testAlterar() {
		assertEquals(HttpStatus.OK, ctrl.alterar("a", new PedidoVO("a")).getStatusCode());
	}

	@Test
	void testExcluir() {
		assertEquals(HttpStatus.OK, ctrl.excluir("a").getStatusCode());
	}

	@Test
	void testAtualizarStatus() {
		assertEquals(HttpStatus.OK, ctrl.atualizarStatus("a", Timer.Status.Stopped).getStatusCode());
	}

	@Test
	void testAtualizarVencimentoPedido() {
		assertEquals(HttpStatus.OK, ctrl.atualizarVencimentoPedido("a", new VencimentoPedidoDto()).getStatusCode());
	}

}
