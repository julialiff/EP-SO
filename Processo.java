public class Processo {
	protected Integer contador_programa;
	protected String nome;
	protected String [] instrucoes;
	protected int x;
	protected int y;
	protected int estado; // 0 - pronto; 1 - bloqueado; 2 - terminado
	protected int timer;

	public Processo(String nome, String [] instrucoes) {
		this._init();
		this._setInstrucoes(instrucoes);
		this.nome = nome;
	}
	
	protected void _init() {
		this.contador_programa = 0;
		this.estado = 0;
	}

	protected void _setInstrucoes(String [] instrucoes) {
		this.instrucoes = instrucoes;
	}

	public void executa(int quantum) {
		while (quantum > 0) {
			String instrucao = instrucoes[contador_programa];
			contador_programa++;

			if (instrucao.equals("E/S")) {
				this.estado = 1;
				this.timer = 2;
				break;
			}

			if (instrucao.equals("SAIDA")) {
				this.estado = 2;
				break;
			}

			if (instrucao.equals("COM")) {

			}

			if (instrucao.matches("X=([0-9]+)")) {
				this.x = Integer.parseInt(instrucao.replaceFirst("X=([0-9]+)", "$1"));
			}

			if (instrucao.matches("Y=([0-9]+)")) {
				this.y = Integer.parseInt(instrucao.replaceFirst("Y=([0-9]+)", "$1"));
			}			

			quantum--;
		}
	}

	public void espera() {
		this.timer--;

		if (this.timer <= 0) {
			this.timer = 0;
			this.estado = 0;
		}
	}

	public int getEstado() {
		return this.estado;
	}

	public String getNome() {
		return this.nome;
	}

}