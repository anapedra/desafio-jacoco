<h1>Desafio DSMovie Jacoco</h1>

    


<h2>Requisitos : </h2>

<p>
Este é um projeto de filmes e avaliações de filmes. A visualização dos dados dos filmes é pública e não requer login. No entanto, as alterações de filmes, como inserir, atualizar e deletar, são permitidas apenas para usuários com perfil ADMIN. As avaliações de filmes podem ser registradas por qualquer usuário logado, seja CLIENT ou ADMIN.

A entidade Score armazena uma nota de 0 a 5 que cada usuário atribui a cada filme. Sempre que um usuário registra uma nota, o sistema recalcula a média das notas de todos os usuários e atualiza a nota média na entidade Movie, juntamente com a contagem de votos.

O projeto deve ser entregue com todos os testes passando, e o Jacoco deve reportar 100% de cobertura de testes. 
 </p>

 <h2>Competências avaliadas: </h2>
  <p>

 - Testes unitários em projeto Spring Boot com Java
 
- Implementação de testes unitários com JUnit e Mockito

- Cobertura de código com Jacoco
  </p>

  <h2>Parte do desenvolvimento que satisfaz o desafil: </h2>
  <p>

 - MovieService.findAll() deve retornar uma página de filmes

- MovieService.findById(id) deve retornar um filme quando o id existir

- MovieService.findById(id) deve lançar ResourceNotFoundException quando o id não existir

- MovieService.insert(dto) deve retornar um filme

- MovieService.update(id, dto) deve retornar um filme quando o id existir

- MovieService.update(id, dto) deve lançar ResourceNotFoundException quando o id não existir

- MovieService.delete(id) deve fazer nada quando o id existir

- MovieService.delete(id) deve lançar ResourceNotFoundException quando o id não existir

- MovieService.delete(id) deve lançar DatabaseException quando o id for dependente

- UserService.authenticated() deve retornar um usuário quando houver usuário logado

- UserService.authenticated() deve lançar UsernameNotFoundException quando não houver usuário logado

- UserService.loadUserByUsername(username) deve retornar um UserDetails quando o username existir

- UserService.loadUserByUsername(username) deve lançar UsernameNotFoundException quando o username não existir

- ScoreService.saveScore(dto) deve retornar os dados do filme quando o id existir

- ScoreService.saveScore(dto) deve lançar ResourceNotFoundException quando o id do filme não existir
  </p>
 

- Entrgar o desafio proposto
- Arquitetar e desenvolver o projeto do zero, usando padroes pertinentes.
- O desenvolvimento será guiado pela abordagem TDD (Test-Driven Development).
- Desenvolver um frontend.
- integração de frontend e backend
- Implementar nas nuvens/aws
  </p>

 </p>
 <h2>Ferramentas utilizadas: </h2>
  <p>

<p >🚀 1-Java-17</p>

<p >🚀 2-Spring Boot-3.2.4</p>

<p >🚀 2-Spring Boot-3.2.4</p>

<p >🚀 3-Spring Data</p>

<p >🚀 4-Spring validetion</p>

<p >🚀 5-Jacoco</p>

<p >🚀 6-Postman</p>

<p >🚀 7-Estilo arquitetural REST</p>

<p >🚀 8-Maven</p>

<p >🚀 10-Linux</p>

<p >🚀 11-IntelliJ</p>

<p >🚀 12-VsCode</p>

<p >🚀 13-H2-perfil/test</p>

<p >🚀 14-Spring Security</p>

<p >🚀 15-Junit5</p>

<p >🚀16-OAuth2</p>

<p >🚀 17-Git/GitHub</p>

<p >🚀 20-Mockito</p>

<h2>Sobre o sistema a ser desenvolvido:</h2>
  <p >

 </p>
