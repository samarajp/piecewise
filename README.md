# Algoritmo estimator-function

<p>
<pre> <code>
Input: timestamp, id_edge, tolerancia, gava_value;
Output: function

1.begin
2.  Seleciona durações de viagens dado id_edge;
3.  Obtem valores mínimo e máximo do horário de partida dado ige_edge;
4.  Se (raiz == null)
5.      Cria_No (x0, media, variancia, desvio_padrao);
7.  Senão
8.      Enquanto (desvio_padrao > tolerancia) faça
9.          Cria_No (x0, media, variancia, desvio_padrao);
10.         update_G(timestamp, start_time, end_time, gama_value);
<\code> <\pre>
