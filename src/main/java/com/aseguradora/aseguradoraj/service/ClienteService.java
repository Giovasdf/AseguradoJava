package com.aseguradora.aseguradoj.service;

import com.aseguradora.aseguradoraj.model.Cliente;
import com.aseguradora.aseguradoj.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> listarClientes() {
        return clienteRepository.findAll();
    }

    public Cliente obtenerClientePorId(Long id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        return cliente.orElse(null);
    }

    public Cliente registrarCliente(Cliente cliente) {
        // Puedes implementar validaciones antes de guardar
        return clienteRepository.save(cliente);
    }

    public Cliente actualizarCliente(Long id, Cliente clienteNuevo) {
        Optional<Cliente> clienteOptional = clienteRepository.findById(id);
        if (clienteOptional.isPresent()) {
            Cliente clienteExistente = clienteOptional.get();
            clienteExistente.setNombre(clienteNuevo.getNombre());
            clienteExistente.setApellido(clienteNuevo.getApellido());
            return clienteRepository.save(clienteExistente);
        }
        return null;
    }

    public boolean eliminarCliente(Long id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        if (cliente.isPresent()) {
            clienteRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
