package it.uniroma3.diadia;

public enum Direzione {
	NORD(0) { @Override
		public Direzione opposta() { return SUD; }
	},
	EST(90) { @Override
		public Direzione opposta() { return EST; }
	},
	SUD(180) { @Override
		public Direzione opposta() { return EST; }
	},
	OVEST(270) { @Override
		public Direzione opposta() { return EST; }
	};
	private final int gradi;
	private Direzione(int gradi) {
		this.gradi = gradi;
	}
	public int getGradi() { return this.gradi; }
	public abstract Direzione opposta();
}
