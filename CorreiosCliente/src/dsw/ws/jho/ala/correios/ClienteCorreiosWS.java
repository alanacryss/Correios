package dsw.ws.jho.ala.correios;

import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.text.DecimalFormat;

import org.tempuri.CResultado;
import org.tempuri.CServico;
import org.tempuri.CalcPrecoPrazoWSSoap;
import org.tempuri.CalcPrecoPrazoWSSoapProxy;

public class ClienteCorreiosWS {
	
	private static final String SEDEX_VAREJO = "40010";
	private static final String SEDEX_A_COBRAR_VAREJO = "40045";
	private static final String SEDEX_10_VAREJO = "40215";
	private static final String SEDEX_HOJE_VAREJO = "40290";
	private static final String PAC_VAREJO = "41106";
	
	private static final String CEP_ORIGEM = "59830000";
	private static final String CEP_DESTINO = "59820000";
	
	private static final String PESO = "2";
	
	private static final int FORMATO_CAIXA = 1;
	private static final int FORMATO_ROLO = 2;
	private static final int FORMATO_ENVELOPE = 3;
	
	private static final BigDecimal COMPRIMENTO = BigDecimal.valueOf(20);
	private static final BigDecimal ALTURA = BigDecimal.valueOf(25);
	private static final BigDecimal LARGURA = BigDecimal.valueOf(30);
	private static final BigDecimal DIAMETRO = BigDecimal.valueOf(29);
	
	private static final String MAO_PROPRIA_SIM = "S";
	private static final String MAO_PROPRIA_NAO = "N";
	
	private static final BigDecimal VALOR_DECLARADO = BigDecimal.valueOf(10);
	
	private static final String AVISO_RECEBIMENTO_SIM = "S";
	private static final String AVISO_RECEBIMENTO_NAO = "N";
	

	public static void main(String[] args) {
		
		CResultado resultado = new CResultado();
		
		CalcPrecoPrazoWSSoap calc = new CalcPrecoPrazoWSSoapProxy();
		
		try {
			resultado = calc.calcPrecoPrazo("", "", PAC_VAREJO, CEP_ORIGEM, CEP_DESTINO, PESO, FORMATO_CAIXA, COMPRIMENTO, 
					ALTURA, LARGURA, DIAMETRO, MAO_PROPRIA_SIM, VALOR_DECLARADO, AVISO_RECEBIMENTO_NAO);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(CServico s : resultado.getServicos()){
			System.out.println("Cód.: " + s.getCodigo());
			System.out.println("Prazo: " + s.getPrazoEntrega() + " dias");
			System.out.println("Valor: R$" + s.getValor());
		}
		
	}

}
