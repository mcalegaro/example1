package com.example.demo.ctrl;

import java.util.LinkedList;
import java.util.List;

import org.apache.logging.log4j.util.Timer.Status;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.vo.PedidoListarDto;
import com.example.demo.vo.PedidoVO;
import com.example.demo.vo.VencimentoPedidoDto;

@RestController
public class PedidoResource {

	@GetMapping(path = "/pedidos/{idPedido}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ServiceResponse<PedidoVO>> consultar(@PathVariable String idPedido) {
		System.out.println(idPedido);
		return ResponseEntity.ok(new ServiceResponse<PedidoVO>("ok", new PedidoVO(idPedido)));
	}

	@GetMapping(path = "/pedidos", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ServicePageableResponse<List<PedidoVO>>> listar(PedidoListarDto pedidoListarDto) {
		System.out.println(pedidoListarDto);
		return ResponseEntity.ok(new ServicePageableResponse<List<PedidoVO>>("1", new LinkedList<>()));
	}

	@PostMapping(path = "/pedidos", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ServiceResponse<Void>> criar(@RequestBody PedidoVO pedido) {
		System.out.println(pedido);
		return ResponseEntity.ok(new ServiceResponse<Void>());
	}

	@PutMapping(path = "/pedidos/{idPedido}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ServiceResponse<Void>> alterar(@PathVariable String idPedido, @RequestBody PedidoVO pedido) {
		return returnSR(idPedido);
	}

	@DeleteMapping(path = "/pedidos/{idPedido}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ServiceResponse<Void>> excluir(@PathVariable String idPedido) {
		return returnSR(idPedido);
	}

	@PatchMapping(path = "/pedidos/{idPedido}/{status}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ServiceResponse<Void>> atualizarStatus(@PathVariable String idPedido,
			@PathVariable Status status) {
		return returnSR(idPedido);
	}

	@PatchMapping(path = "/pedidos/{idPedido}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ServiceResponse<Void>> atualizarVencimentoPedido(@PathVariable String idPedido,
			@RequestBody VencimentoPedidoDto vencimentoPedidoDto) {
		return returnSR(idPedido);
	}

	private ResponseEntity<ServiceResponse<Void>> returnSR(String idPedido) {
		System.out.println(idPedido);
		return ResponseEntity.ok(new ServiceResponse<Void>(idPedido, null));
	}

}
