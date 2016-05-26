#java-websocket
Introdução a WebSockets, utilizando JAVA API para WebSockets.

##Problema
Conexões de baixa latência de cliente-servidor.

> Mas o que é latência?
> Em uma rede, latência determina o tempo que leva para um pacote de dados ir de um ponto da conexão até o o outro. 

A web foi moldada em cima do protocolo baseado em solicitação/resposta denominado HTTP. Um cliente carrega uma página da web e, em seguida, nada acontece até que o usuário clique na próxima página.

No protocolo HTTP toda vez que eu quero um determinado recurso, por exemplo, uma lista de pessoas o cliente precisa montar um cabeçalho de solicitação e enviar pro servidor. Nesse momento é aberta uma conexão entre cliente-servidor. O servidor recebe esse cabeçalho e verifica se está tudo certo . Caso estiver, busca a lista de pessoas de algum lugar e anexa tal lista a um cabeçalho de resposta. Envia tudo isso para o cliente e depois disso a conexão entre cliente-servidor é fechada. 

> Lembrando que esses cabeçalhos precisam estar de acordo com as [definições do HTTP](https://www.w3.org/Protocols/rfc2616/rfc2616.html).

Esse processo de solicitação-resposta se repete basicamente a cada ação em uma página da web, tornando o HTTP uma má escolha para aplicações que trabalham com tempo real.

> Foda-se,eu odeio WebSockets e quero fazer um Chat usando HTTP.

![alt tag](https://raw.githubusercontent.com/username/projectname/branch/path/to/img.png)




Um dos problemas mais comuns para criar a ilusão de uma conexão iniciada pelo servidor é a chamada sondagem longa (mais conhecido como long poolling). Com a sondagem longa, o cliente abre uma conexão HTTP com o servidor que permanece aberta até que a resposta seja enviada.

A sondagem longa e as outras técnicas funcionam muito bem. No entanto, todas essas soluções compartilham um problema: elas carregam a sobrecarga de HTTP, que não é adequada para aplicativos de baixa latência. Pense em jogos com vários jogadores no navegador ou em qualquer outro jogo on-line com um componente em tempo real.


##Solução
A especificação WebSocket define uma API que estabelece conexões de "soquete" entre um navegador da web e um servidor. Em outras palavras, há uma conexão persistente entre o cliente e o servidor e ambas as partes podem começar a enviar dados a qualquer momento.

