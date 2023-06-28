

## Sistema de Delivery

cadastro de produtos, clientes, empresas, e entregadores, e visualização de pedidos

sistema de autenticação com 4 niveis de permissao : 
#### Admin ,Cliente, Empresa e Entregador  
obs: admin pode realizar todas as requisições

## Documentacao Completa: http://localhost:8080/swagger-ui/

### obs: endpoints todos tem : http://localhost:8080

para usar o sistema necessario necessario dos logins passando  o json : 

Auth controller
## Authentication User Controller

### endpoint =  POST /auth  

login admin :  
{  
"email":"admin",  
"senha":"123456"  
}  
login cliente :  
{  
"email":"emailCliente",  
"senha":"123456"  
}  
login empresa :   
{   
"email":"emailEmpresa",  
"senha":"123456"  
}  
login entregador :   
{    
"email":"emailEntregador",  
"senha":"123456"  
}  

### GET  ​/auth   Visualizar Perfils


## Cliente Controller

### POST​ /cliente  cadastrar Cliente

{
  "endereco": {
    "cep": "string",  
    "complemento": "string",  
    "numero": "string"  
  },  
  "id": 0,  
  "nome": "string"  
}  


### GET  /cliente/pedido/naoEntregue  paginação pedidos nao entregues


## Empresa Controller

### POST  ​/empresa  cadastrarEmpresa

body
Model
{
  "cnpj": "string",  
  "email": "string",  
  "endereco": {  
    "bairro": "string",  
    "cep": "string",  
    "complemento": "string",  
    "localidade": "string",  
    "logradouro": "string",  
    "numero": "string"  
  },  
  "id": 0,  
  "nome": "string",  
  "produtos": [  
    {  
      "descricao": "string",  
      "id": 0,  
      "nome": "string",   
      "quantidade": 0  
    }
  ],
  "statusLoja": "ABERTO"
}


### GET :  ​/empresa 
EmpresasDisponiveis mostra empresas que estao abertas

retorno 
{
  "content": [   
    {  
      "cnpj": "string",  
      "email": "string",  
      "endereco": {  
        "bairro": "string",  
        "cep": "string",  
        "complemento": "string",  
        "localidade": "string",  
        "logradouro": "string",  
        "numero": "string"  
      },  
      "id": 0,  
      "nome": "string",  
      "produtos": [  
        {  
          "descricao": "string",  
          "id": 0,  
          "nome": "string",  
          "quantidade": 0  
        }  
      ],  
      "statusLoja": "ABERTO"   
    }  
  ],  

# Teste Trainee

Avaliação Prática - Desenvolvedor Backend
Parabéns por chegar à próxima etapa do processo de recrutamento da Lampp-it!  
Preparamos um exercício simples para entender melhor como você aborda a solução 
de problemas, pensamento criativo e atenção aos detalhes.  

## Problemática  
A empresa Acme Ltda deseja desenvolver um sistema de pedidos online com  
entregas. Nesse sistema haverá um módulo onde serão cadastradas as empresas que  
desejam vender pelo aplicativo, elas poderão cadastrar os produtos ofertados   
Já os usuários ao acessar o menu no aplicativo serão listadas as empresas  
cadastradas e ao clicar numa empresa é necessário listar os produtos ofertados.  
Os usuários também poderão selecionar um ou mais produtos para assim finalizar o  
pedido. Nesse pedido deverão conter os produtos selecionados, a forma de  
pagamento (Dinheiro, cartão ou pix) e a forma de entrega (Delivery, Pegar no local).  
As empresas poderão acessar uma lista de pedidos em andamento, e selecionar um  
pedido para atendimento, ao atender deverá trocar o status do produto de cadastrado  
para “Em Atendimento”, quando o produto estiver pronto a empresa deverá alterar o  
status para o Concluído.   
O usuário deverá ter uma tela para listar os pedidos que ainda não forem entregues  
para ficar acompanhando.  
O entregador poderá listar pedidos disponíveis para entrega e aceitar o serviço. O  
entregador ao entregar o pedido deve informar no aplicativo para que a loja  
acompanhe o andamento das entregas.  

## Regras de negócio   
 O Sistema não deverá permitir o usuário realizar um pedido sem produtos.  
 O sistema não deverá permitir o usuário realizar pedidos fora do horário de  
funcionamento da loja.  
 O sistema não deverá permitir os status de um pedido voltar a um status anterior.  
 O Sistema não deverá permitir realizar pedidos sem uma forma de pagamento.  
 O Sistema não deverá permitir realizar pedidos sem um método de entrega.  
 O Sistema não deverá exibir empresas sem produtos cadastrados.  
 O Sistema deverá permitir um mesmo cliente tenha mais de um pedido em andamento.  
 O entregador só poderá atender pedidos associados a ele.  
