#java-websocket
Introdução a WebSockets, utilizando JAVA API para WebSockets.

##Que problema WebSockets resolvem?
Conexões de alta latência de cliente-servidor, situação (alta latência) muito comum no protocolo HTTP.

> Bacana, mas o que é latência?
> Em uma rede, latência determina o tempo que leva para um pacote de dados ir de um ponto da conexão até o o outro. 

##O problema mais detalhado

A web foi moldada em cima do protocolo baseado em solicitação/resposta denominado HTTP. Um cliente carrega uma página da web e, em seguida, nada acontece até que o usuário clique na próxima página.

No protocolo HTTP toda vez que eu quero um determinado recurso, por exemplo, uma lista de pessoas o cliente precisa montar um **cabeçalho de solicitação** e enviar pro servidor. Nesse momento é aberta uma conexão entre cliente-servidor. O servidor recebe esse cabeçalho e verifica se está tudo certo. Caso estiver, busca a lista de pessoas de algum lugar e anexa tal lista a um **cabeçalho de resposta**. Envia tudo isso para o cliente e depois disso a conexão entre cliente-servidor é fechada. 

> Só pra lembrar, esses cabeçalhos precisam estar de acordo com as [definições do HTTP](https://www.w3.org/Protocols/rfc2616/rfc2616.html).

Esse processo de **solicitação-resposta** se repete basicamente a cada ação em uma página da web, tornando o HTTP uma má escolha para aplicações que trabalham com tempo real.

> Foda-se, eu odeio WebSockets e quero fazer um Chat usando o protocolo HTTP.
> ![Foda-se](https://github.com/jeancasulo/java-websocket/blob/master/imagens/fodase.png?raw=true) <br/>
> Como eu faço?

Diante desta afirmação meu ilustre amigo, de uma maneira nada elegante, você teria que fazer o lado cliente da sua conexão, perguntar via solicitação HTTP, se há alguma mensagem nova para o servidor periódicamente.

> Cliente: Eu como cliente inicio uma nova conexão e, reivindico novas mensagens. <br/>
> Servidor: Eu como servidor lhe digo que não tens nada para receber. <br/>
> -- Conexão finalizada <br/>
> Cliente: Eu como cliente inicio uma nova conexão, reivindico novas mensagens. <br/>
> Servidor: Eu como servidor lhe digo que tens novas mensagens para serem recebidas. Recebe-as. <br/>
> -- Conexão finalizada

Você poderia implementar isso de uma maneira menos extravagante utilizando, por exemplo, a tecnologia [Commet](https://en.wikipedia.org/wiki/Comet_(programming)) baseada em sondagem longa (long-polling). As tecnologias de sondagem longa permitem que o servidor envie dados ao cliente, no momento que descobre que novos dados estão disponíveis. Com a sondagem longa, o cliente incia uma nova conexão com o servidor, e essa conexão permanece aberta até que a resposta seja enviada. Sempre que tem novos dados, o servidor envia a resposta.

> Cliente: Eu como cliente inicio uma nova conexão e fico a espera por respostas. Mantenha a conexão aberta! <br/>
> -- Algum tempo depois... <br/>
> Servidor: Eu como servidor lhe digo que tens novas mensagens para serem recebidas. Recebe-as.

Essas técnicas funcionam, avá. Porém elas sobrecarregam o nosso servidor HTTP e aumentam a latência já comentada lá no comecinho dessa
página. Com a alta latência não seria possível saborear uma aplicação de tempo real da melhor maneira. 

##Solução
A especificação WebSocket define uma API que estabelece conexões de "soquete" entre um navegador da web e um servidor. Em outras palavras, há uma conexão persistente entre o cliente e o servidor e ambas as partes podem começar a enviar dados a qualquer momento.

