# PasswordLocker-Api

## O que é o PasswordLocker?

​	é um site desenvolvido para armazenamento de senhas, com o intuito de aprender mais sobre tecnologias mais de "Base" com html, css, javaScript para front e  Java e MySQL para back e também explorar o mundo dos frameworks e criptografias que no nosso caso estamos criptografando nosso dados com um Base64 com a forma de criptografia AES (Advanced Encryption Standard) que é um tipo de criptografia que usa a mesma chave para criptografar e descriptografar os dados.

​	Este repositório se trata do backend de nosso projeto, o frontend pode ser encontrado neste <a href = "https://github.com/maricsouza/PI-Password-Locker">repositório.</a>

#### Tecnologias usadas

- MySql
- Spring boot
- Java
- Maven

## Como rodar o projeto?

#### 1° Precisamos ter alguns programas instalados

- <a href = "https://dev.mysql.com/downloads/workbench/">MySQL Workbanch</a>
- <a href ="https://www.eclipse.org/downloads/">Eclipse IDE</a> ou <a href="https://code.visualstudio.com/download">Visual Studio Code</a>
  -  Caso use o Visual Studio Code você precisara das seguintes extensões:
    - <a href = "https://marketplace.visualstudio.com/items?itemName=vscjava.vscode-java-pack">Extension Pack for Java</a>
    - <a href="https://marketplace.visualstudio.com/items?itemName=Pivotal.vscode-boot-dev-pack">Spring Boot Extension Pack</a>

- <a href="https://www.postman.com/downloads/">Postman</a>

​	(Claramente você também terá que ter instalado em sua maquina o Git para clonar o projeto e o Java para o projeto)

#### 2° Clonando o projeto

- Visual Studio Code

Primeira mente va ao github e pege o link deste repositorio, apos isso volte no vs code e clique em "Clone Git Repository"<br>
![Screenshot_11](https://user-images.githubusercontent.com/81272272/202070768-f00942ba-d55a-45e5-b53f-05c6d989965f.png)

Cole o link nessa janelinha que ira aparecer <br>
![image](https://github.com/TiagoFernandes11/PasswordLocker-Api/assets/81272272/068cf774-214c-4fd1-b43f-eef8c7fff49a)

Clique em uma dessas opções, ai fica de sua escolha<br>
![Screenshot_13](https://user-images.githubusercontent.com/81272272/202070774-ba804618-634a-46f4-bffa-c394e6d85c20.png)

E pronto projeto clonado <br>
![image](https://github.com/TiagoFernandes11/PasswordLocker-Api/assets/81272272/45159090-216b-41bb-a7bf-40dfdbf2633b)


#### 3° Configurando banco de dados e conexão

- Primeiro vamos rodar o script

Vá nas pastas do projeto e procure pelo arquivo "Script_DB.sql" e copie todo sciprt que estiver lá<br>
![image](https://github.com/TiagoFernandes11/PasswordLocker-Api/assets/81272272/8cedff8c-7041-4776-8ae5-77779cde612e)

Abra o Workanch abra uma nova aba de query e cole o script lá, va em "Schemas" para poder ver se ele foi criado 
Após isso clique esse raiozinho para execultar todo o script<br>
![image](https://github.com/TiagoFernandes11/PasswordLocker-Api/assets/81272272/b6b07943-cf52-4fc6-a4d0-c764d8063b4a)

caso ainda não tenha aparecido, clique em atualizar (Sinalizado pela seta verde)<br>
![Screenshot_31](https://user-images.githubusercontent.com/81272272/202071083-316f379e-5001-4df5-9970-adc6362ebdda.png)

um ultima passo agora é olhar nesta aba "Session"<br>
![image](https://user-images.githubusercontent.com/81272272/202077927-d8afcda4-a989-4ea0-aab1-3da7fff772b9.png)

e verificar essas informaçoes para mais tarde<br>
![image](https://user-images.githubusercontent.com/81272272/202077983-9d9156b8-aa01-441e-b413-d094b1e33e2a.png)


- Agora vamos configurar a conexão com o banco de dados 

vá ao projeto e procure pelo arquivo "application.properties" e o abra<br>
![image](https://github.com/TiagoFernandes11/PasswordLocker-Api/assets/81272272/6548316b-7529-4e01-ada2-3b64e77e1e79)

assim que abrir e preencha os compos conforme o seu banco de dados sua senha a porta aonde seu banco está rodando e o local aonde está rodando<br>
![Screenshot_34](https://user-images.githubusercontent.com/81272272/202071153-7cfa94ed-bac9-425a-bb2f-5b50cee1ddb0.png)


#### 4° Rodando o projeto

​	Nesta etapa por enquanto só rodarei no visual studio Code, pois é aonde funciona melhor	para nos


Procure o arquivo "PasswordlockerApplication.java" e abra ele<br>
![image](https://github.com/TiagoFernandes11/PasswordLocker-Api/assets/81272272/12ab7f79-0ebd-47ec-a276-c402a5a3bfb3)

Apos estar com o documento aberto, cloque no plazinho no canto superior direito da tela<br>
![image](https://github.com/TiagoFernandes11/PasswordLocker-Api/assets/81272272/55b6c7a0-961c-4289-b951-64580d1718dc)

E pronto seu projeto está rodando!<br>
![image](https://github.com/TiagoFernandes11/PasswordLocker-Api/assets/81272272/5828be36-f33e-49a0-af82-0ace5a70b14a)

#### 4° Testando o projeto

Copie este link:
``` https://api.postman.com/collections/23992032-90c47375-31d6-4dd5-ab67-eacaa9d4c352?access_key=PMAT-01H1YVY7AKW24B9A9BSH8Y0NFZ ```


Entre no seu postman e vá ao import e clique nele<br>
![Screenshot_6](https://user-images.githubusercontent.com/81272272/208815473-071e7735-90e7-4443-b9c7-a5a3a5915508.png)

Apos clicar em import vá é cole o link Link<br>
![image](https://github.com/TiagoFernandes11/PasswordLocker-Api/assets/81272272/29456e9c-ac05-47e6-8a9c-e3b711107c3a)

e ele ira importar automaticamente(nas versoes mais atuais)
E pronto a collection está em seu postman agora é só testar a vontade<br>
![image](https://github.com/TiagoFernandes11/PasswordLocker-Api/assets/81272272/76acc9ce-c6ee-40e3-a6ce-c66d8bd489c0)

## Envolvidos no back-end
- Tiago Fernandes<a href="https://www.linkedin.com/in/tiago-fernandes-ribeiro-03074815a/">💼Linkedin</a> <a href ="https://github.com/TiagoFernandes11">👩‍💻Github</a> 

- Nathalia da Rocha Santos<a href="">💼Linkedin</a> <a href ="https://github.com/NathaliadaRocha07">👩‍💻Github</a>

- Raian Medeiros Nolaço<a href="https://www.linkedin.com/in/raiannolaço/">💼Linkedin</a> <a href ="https://github.com/RaianNolaco?tab=repositories">👨‍💻Github</a>
