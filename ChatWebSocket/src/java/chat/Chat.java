/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 *
 * @author JeanCarlos
 */
@ServerEndpoint("/chat")
public class Chat {
    //Conjunto sincronizado por causa dos problemas de concorrência
    private static Set<Session> sessoes = Collections.synchronizedSet(new HashSet<Session>());

    @OnMessage
    public void mensagemRecebida(String mensagem, Session sessao) {
        System.out.println("Mensagem recebida: " + mensagem +". Da sessão "+sessao.getId());
        for (Session s : sessoes){
            System.out.println("Enviando mensagem para: "+s.getId());
            //Ganha uma instância assíncrona do EndPoint e envia a mensagem
            s.getAsyncRemote().sendText(mensagem);
        }
    }
    
    @OnClose
    public void sessaoFechada(Session sessao){
        System.out.println("Sessão fechada: "+sessao.getId());
        sessoes.remove(sessao);
    }
    
    @OnOpen
    public void sessaoAberta(Session sessao){
        System.out.println("Sessão aberta: "+sessao.getId());
        sessoes.add(sessao);
    }
}
