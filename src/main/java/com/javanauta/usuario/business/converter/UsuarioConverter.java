package com.javanauta.usuario.business.converter;


import com.javanauta.usuario.business.dto.EnderecoDTO;
import com.javanauta.usuario.business.dto.TelefoneDTO;
import com.javanauta.usuario.business.dto.UsuarioDTO;
import com.javanauta.usuario.infrastructure.entity.Endereco;
import com.javanauta.usuario.infrastructure.entity.Telefone;
import com.javanauta.usuario.infrastructure.entity.Usuario;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component

public class UsuarioConverter{

    public UsuarioDTO paraUsuarioDTO(Usuario usuarioDTO){
        return UsuarioDTO.builder()
                .nome(usuarioDTO.getNome())
                .email(usuarioDTO.getEmail())
                .senha(usuarioDTO.getSenha())
                .enderecos(paraListaEnderecoDTO(usuarioDTO.getEnderecos()))
                .telefone(paraListaTelefoneDTO(usuarioDTO.getTelefones()))

                .build();
    }

    public List<EnderecoDTO> paraListaEnderecoDTO(List<Endereco> enderecoDTOS){
        // podemos passar assim
//return enderecoDTOS.stream().map(this::paraEndereco).toList();

// OU podemos utilizar como FOR declarando um list de endereço para cade endereço DTO agente faz a conversao de endereço, adicionado para nossa lista de endereço, depois passamos um retorno endereço.

        List<EnderecoDTO> enderecos = new ArrayList<>();
        for (Endereco EnderecoDTO : enderecoDTOS) {
            enderecos.add(paraEnderecoDTO(EnderecoDTO));
        }
        return enderecos;
    }
    public EnderecoDTO paraEnderecoDTO(Endereco enderecoDTO){
        return EnderecoDTO.builder()
                .rua(enderecoDTO.getRua())
                .numero(String.valueOf(enderecoDTO.getNumero()))
                .cidade(enderecoDTO.getCidade())
                .complemento(enderecoDTO.getComplemento())
                .cep(enderecoDTO.getCep())
                .estado(enderecoDTO.getEstado())
                .build();
    }
    public List<TelefoneDTO> paraListaTelefoneDTO(List<Telefone> telefoneDTOS){
return telefoneDTOS.stream().map(this::paraTelefoneDTO).toList();
    }
    public TelefoneDTO paraTelefoneDTO(Telefone telefoneDTO){
        return TelefoneDTO.builder()
                .numero(telefoneDTO.getNumero())
                .ddd(telefoneDTO.getDdd())
                .build();
    }
}