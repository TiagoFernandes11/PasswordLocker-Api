# PasswordLocker-Api

Link do projeto completo : https://github.com/maricsouza/PI-Password-Locker

Link publico do postaman online : https://www.postman.com/aerospace-geologist-46347935/workspace/areapublica/collection/23992032-be6a1d8e-6153-4258-b90f-cecb95d4a99e

Link para importar a biblioteca : https://api.getpostman.com/collections/23992032-c0e7d850-f16e-4933-b806-ec4ca7a62e9d

## O que é o PasswordLocker?

​	é um site desenvolvido para armazenar senhas, com o intuito de aprender mais sobre tecnologias mais de "Base" com html, css, javaScript para front e  Java e MySQL para back e também explorar o mundo dos frameworks e criptografias que no nosso caso estamos criptografando nosso dados com um Base64 com a forma de criptografia AES (Advanced Encryption Standard) que é um tipo de criptografia que usa a mesma chave para criptografar e descriptografar os dados.

​	Este repositório se trata do backend de nosso projeto, o frontend pode ser encontrado neste <a href = "https://github.com/KevinAlvss/our-bank-frontend">repositório.</a>

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
![Screenshot_12](https://user-images.githubusercontent.com/81272272/202070772-8024f09a-a9dc-42a9-993f-14d6bf223d3b.png)

Clique em uma dessas opções, ai fica de sua escolha<br>
![Screenshot_13](https://user-images.githubusercontent.com/81272272/202070774-ba804618-634a-46f4-bffa-c394e6d85c20.png)

E pronto projeto clonado <br>
![Screenshot_20](https://user-images.githubusercontent.com/81272272/202079121-3a6550d7-6dd8-4ecf-8d48-59d743572006.png)



- Ecliplse 

(A forma como será mostrada é uma forma mais "Arcaica" de se fazer mas funciona)
Vá a pagina do repositorio e baixe o projeto como ZIP, apos isso descompacte o projeto em um diretorio de sua preferencia<br>
![Screenshot_21](https://user-images.githubusercontent.com/81272272/202079208-d27040ec-063d-4792-bb39-ebb44e4fb749.png)

Apos isso, va ao seu eclipse e clique em File <br>
![Screenshot_22](https://user-images.githubusercontent.com/81272272/202079209-a3e35e20-4aa8-4a21-99cd-c62f6ca71146.png)

vá em Import<br>
![Screenshot_23](https://user-images.githubusercontent.com/81272272/202079211-bba349f6-77f6-48db-bbff-b53d8ed70e72.png)

Clique em "Maven" > "Existing Maven Projects" e cliquie em next<br>
![Screenshot_24](https://user-images.githubusercontent.com/81272272/202079212-880ac6ea-c5ec-4cbe-b19a-dabc87050c4d.png)

Clique em Browse e selecione o repositorio que você colocou o projeto<br>
![Screenshot_26](https://user-images.githubusercontent.com/81272272/202079218-dcf41e72-a158-4b6d-a168-c2c195e684b2.png)

e pronto você está com o projeto no eclipse <br>
![Screenshot_27](https://user-images.githubusercontent.com/81272272/202079219-a9a5eeed-2a3e-4278-812a-355112978839.png)


#### 3° Configurando banco de dados e conexão

- Primeiro vamos rodar o script

Vá nas pastas do projeto e procure pelo arquivo "Script.sql" e copie todo sciprt que estiver lá<br>
![Screenshot_28](https://user-images.githubusercontent.com/81272272/202076439-3fcb2fd7-2690-400c-ab75-e99bce0398ab.png)

Abra o Workanch abra uma nova aba de query e cole o script lá<br>
![Screenshot_29](https://user-images.githubusercontent.com/81272272/202071077-85339aee-6a4f-463a-bb53-0c29a9f00194.png)

va em "Schemas" para poder ver se ele foi criado 
Após isso clique esse raiozinho para execultar todo o script<br>
![Screenshot_30](https://user-images.githubusercontent.com/81272272/202071082-87b6e23c-8642-4272-81aa-b22884bb0944.png)

caso ainda não tenha aparecido, clique em atualizar (Sinalizado pela seta verde)<br>
![Screenshot_31](https://user-images.githubusercontent.com/81272272/202071083-316f379e-5001-4df5-9970-adc6362ebdda.png)

um ultima passo agora é olhar nesta aba "Session"<br>
![image](https://user-images.githubusercontent.com/81272272/202077927-d8afcda4-a989-4ea0-aab1-3da7fff772b9.png)

e verificar essas informaçoes para mais tarde<br>
![image](https://user-images.githubusercontent.com/81272272/202077983-9d9156b8-aa01-441e-b413-d094b1e33e2a.png)


- Agora vamos configurar a conexão com o banco de dados 

vá ao projeto e procure pelo arquivo "application.properties" e o abra<br>
![Screenshot_33](https://user-images.githubusercontent.com/81272272/202071149-7285e810-7e69-4a5d-8055-8eb642bdf935.png)

assim que abrir e preencha os compos conforme o seu banco de dados sua senha a porta aonde seu banco está rodando e o local aonde está rodando<br>
![Screenshot_34](https://user-images.githubusercontent.com/81272272/202071153-7cfa94ed-bac9-425a-bb2f-5b50cee1ddb0.png)


#### 4° Rodando o projeto

​	Nesta etapa por enquanto só rodarei no visual studio Code, pois é aonde funciona melhor	 

Clique nesse botão azul <br>
![Screenshot_14](https://user-images.githubusercontent.com/81272272/202070776-1c6bc394-4885-40c1-9daa-5a21a031ede3.png)

Procure o arquivo "OurBankApiApplication.java" e abra ele<br>
![Screenshot_15](https://user-images.githubusercontent.com/81272272/202070777-f0aeb2b6-11f2-4380-8c6a-c06db10bed80.png)
![Screenshot_16](https://user-images.githubusercontent.com/81272272/202070778-552ee863-ed5b-4c3b-ba1d-bf2c675a138e.png)
![Screenshot_17](https://user-images.githubusercontent.com/81272272/202070780-1ffef9ad-ab17-4b3a-99bf-1c3bea5d9e8a.png)

Apos estar com o documento aberto, cloque no plazinho no canto superior direito da tela<br>
![Screenshot_18](https://user-images.githubusercontent.com/81272272/202070782-68ef6852-8351-4ef8-8565-a93f1089852c.png)

E pronto seu projeto está rodando!<br>
![Screenshot_19](https://user-images.githubusercontent.com/81272272/202070783-64f281b5-bd3e-4657-813e-30bf487fa024.png)

#### 4° Testando o projeto

Copie este link:
``` https://api.postman.com/collections/23992032-9cf12ff9-fdaa-4b23-8305-e7285be62d15?access_key=PMAT-01GMS9SRNQ9589JE5QASB0FD4R```


Entre no seu postman e vá ao import e clique nele<br>
![Screenshot_6](https://user-images.githubusercontent.com/81272272/208815473-071e7735-90e7-4443-b9c7-a5a3a5915508.png)

Apos clicar em import vá em Link<br>
![Screenshot_7](https://user-images.githubusercontent.com/81272272/208815941-5e07037a-ad35-4d76-9a33-5f16294a77d0.png)

Cole o link nesta caixa de texto<br>
![Screenshot_8](https://user-images.githubusercontent.com/81272272/208815997-8d19e092-eeb7-41f2-80c9-0ef7839252d7.png)

E clique em "Continue"<br>
![Screenshot_9](https://user-images.githubusercontent.com/81272272/208816054-b9bc5514-ff7f-4e43-be70-bd13cdbea78a.png)

Clique em "Import"<br>
![Screenshot_10](https://user-images.githubusercontent.com/81272272/208816080-cb785af6-a267-425f-ac4f-a4b4767e6d45.png)

E pronto a collection está em seu postman agora é só testar a vontade<br>
![Screenshot_11](https://user-images.githubusercontent.com/81272272/208816119-85f524d6-892e-41e5-9778-6882f88e2dc7.png)<br>
![image](https://user-images.githubusercontent.com/81272272/208816402-7cead798-f150-4e5c-9d20-19eaac5bbc1e.png)



