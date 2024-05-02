package br.com.e2etreinamentos.library_e2e.exception;

public class CadastroLivroException  extends RuntimeException{
	
	public CadastroLivroException(String message) {
        super(message);
    }

    public static class LivroJaCadastradoException extends CadastroLivroException {
        public LivroJaCadastradoException() {
            super("Livro já cadastrado com o mesmo título e autor.");
        }
    }

    public static class CamposInvalidosException extends CadastroLivroException {
        public CamposInvalidosException() {
            super("Campos inválidos. Verifique se todos os campos foram preenchidos corretamente.");
        }
    }

    public static class QuantidadeInvalidaException extends CadastroLivroException {
        public QuantidadeInvalidaException() {
            super("A quantidade do livro deve ser maior que zero.");
        }
    }


}
