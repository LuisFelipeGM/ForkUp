# 🎯 Abordagem de Validação com Grupos

## Problema
O `UsuarioVO` recebe a senha obrigatória para cadastro, mas no UPDATE a senha será alterada em um endpoint diferente.

## Solução Implementada: Grupos de Validação ✅

### Por que essa abordagem?
- **Menos duplicação**: Um único VO para CREATE e UPDATE
- **Mais elegante**: Segue padrões modernos do Jakarta Validation
- **Fácil de manter**: Mudanças em um único lugar
- **Explícito**: Fica claro qual campo é obrigatório em cada operação

---

## Como usar?

### 1️⃣ No CREATE (POST /api/v1/usuarios)
```json
{
  "nome": "João Silva",
  "email": "joao@email.com",
  "login": "joao123",
  "senha": "senha123",
  "tipoUsuario": "CLIENTE"
}
```
✅ **Senha é OBRIGATÓRIA**

### 2️⃣ No UPDATE (PUT /api/v1/usuarios/{id})
```json
{
  "nome": "João Silva",
  "email": "joao@email.com",
  "login": "joao123",
  "tipoUsuario": "CLIENTE"
}
```
✅ **Senha é OPCIONAL** (será alterada em outro endpoint)

---

## Implementação no Código

### UsuarioVO.java
Adicionamos **interfaces de grupos**:
```java
public interface Create {}
public interface Update {}
```

Então aplicamos validação condicional:
```java
@NotNull(groups = Create.class, message = "Senha é obrigatória no cadastro")
private String senha;
```

### UsuarioController.java
- Adicione `@Validated` na classe
- Use `@Valid` no método (o Spring detecta automaticamente o grupo correto)

```java
@PostMapping
public UsuarioDTO cadastrar(@Valid @RequestBody UsuarioVO usuarioVO) { }

@PutMapping("/{id}")
public UsuarioDTO atualizar(@PathVariable Long id, @Valid @RequestBody UsuarioVO usuarioVO) { }
```

---

## Alternativa: VOs Separados
Se preferir mais explicitação, você pode criar:
- `UsuarioCreateVO` - com senha obrigatória
- `UsuarioUpdateVO` - sem senha

**Porém**, isso resultaria em duplicação de código. A abordagem de grupos é mais simples.

---

## Endpoint para Alterar Senha
Crie um endpoint separado e dedicado:

```java
@PutMapping("/{id}/senha")
@ResponseStatus(HttpStatus.OK)
public void alterarSenha(@PathVariable Long id, @RequestBody @Valid AlterarSenhaVO alterarSenhaVO) {
    usuarioService.alterarSenha(id, alterarSenhaVO);
}
```

Onde `AlterarSenhaVO`:
```java
@Data
public class AlterarSenhaVO {
    @NotNull
    private String senhaAtual;
    
    @NotNull
    private String novaSenha;
}
```

---

## Validação em Tempo de Execução

A validação é feita **automaticamente** pelo Spring quando:
1. REQUEST POST → Valida com o grupo `Create.class`
2. REQUEST PUT → Valida com o grupo `Update.class`

Se um campo obrigatório estiver faltando, você receberá um erro 400 Bad Request do Spring automaticamente.


