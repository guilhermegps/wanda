package br.com.projeto.wanda;

import java.io.Serializable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author <a href="https://github.com/guilhermegps"> Guilherme GPS </a>
 * @since 1.1
 *
 */
public class WLogger implements Serializable {

	private static final Log log = LogFactory.getLog(WLogger.class);
	private static final String STACK = "\n    StackTrace: \n";

	private enum SeverityEnum {
		INFO(1),
		WARN(2),
		ERROR(3),
		DEBUG(4);

		private final int codigo;

		SeverityEnum(int codigo) {
			this.codigo = codigo;
		}

		int getCodigo() {
			return codigo;
		}
	}

	private static void logger(Object o, SeverityEnum severity) {
		if (SeverityEnum.WARN.equals(severity)) {
			log.warn(getMensagem(o));
		} else if (SeverityEnum.ERROR.equals(severity)) {
			log.error(getMensagem(o));
		} else {
			log.info(getMensagem(o));
		}

	}

	private static String getMensagem(Object o) {
		StringBuilder msg = new StringBuilder();
		if (o instanceof String) {
			msg.append(o);
		} else if (o instanceof Exception) {
			String titleException = "\n\n\n\n   ##### >>>> " + o.getClass().getName() + " ##### <<<<    ";
			StringBuilder stack = new StringBuilder();
			try {
				String causa = ((Exception) o).getCause().getMessage();
				stack.append("\t\t").append(causa).append(System.lineSeparator());

			}catch (Exception e){
				stack.append("\t\t").append(((Exception) o).getMessage()).append(System.lineSeparator());
			}
			for (int i = 0; i < ((Exception) o).getStackTrace().length; i++) {
				if (isStakSnrPackage(((Exception) o).getStackTrace()[i])) {
					stack.append("        ").append(((Exception) o).getStackTrace()[i].toString()).append(System.lineSeparator());
				}
			}
			msg.append(titleException).append((!stack.toString().isEmpty() ? STACK + stack.toString() : "")).
							append(System.lineSeparator()).
							append(System.lineSeparator()).
							append(System.lineSeparator());
		} else {
			return String.valueOf(o);
		}
		return msg.toString();
	}

	private static String getMensagemNull() {
		StackTraceElement[] stack = Thread.currentThread().getStackTrace();
		String title = "\n\n\n\n   ##### >>>> " + stack.getClass().getCanonicalName() + " ##### <<<<    ";
		StringBuilder stackTrace = new StringBuilder();
		for (StackTraceElement aStack : stack) {
			if (isStakSnrPackage(aStack)) {
				stackTrace.append("       ERROR: (").append(aStack.toString()).append(")\n");
			}
		}
		return title + STACK + (!stackTrace.toString().isEmpty() ? stackTrace.toString() : "Nao foi lancada por um pacote do sistema");
	}

	private static void validateAndExecute(Object o, SeverityEnum severity) {
		if (o != null) {
			logger(o, severity);
		} else {
			logger(getMensagemNull(), SeverityEnum.ERROR);
		}
	}

	private static boolean isStakSnrPackage(StackTraceElement stack) {
		return ("br.com.".equalsIgnoreCase(stack.toString().substring(0, 7))
						|| "https.wbs_detrannet".equalsIgnoreCase(stack.toString().substring(0, 18))
						|| "api_integracao".equalsIgnoreCase(stack.toString().substring(0, 13)))
						&& !"br.com.snrng.utils.SnrLogger.".equalsIgnoreCase(stack.toString().substring(0, 29));

	}

	/** @param o **/
	public static <T extends Exception> void info(T o) {
		validateAndExecute(o, SeverityEnum.INFO);
	}

	/** @param o **/
	public static <T extends Exception> void warn(T o) {
		validateAndExecute(o, SeverityEnum.WARN);
	}

	/** @param o **/
	public static <T extends Exception> void error(T o) {
		validateAndExecute(o, SeverityEnum.ERROR);
	}

	/** @param o **/
	public static <T extends Exception> void debug(T o) {
		validateAndExecute(o, SeverityEnum.DEBUG);
	}

	/** @param o **/
	public static <T extends String> void info(T o) {
		validateAndExecute(o, SeverityEnum.INFO);
	}

	/** @param o **/
	public static <T extends String> void warn(T o) {
		validateAndExecute(o, SeverityEnum.WARN);
	}

	/** @param o **/
	public static <T extends String> void error(T o) {
		validateAndExecute(o, SeverityEnum.ERROR);
	}

	/** @param o **/
	public static <T extends String> void debug(T o) {
		validateAndExecute(o, SeverityEnum.DEBUG);
	}

	/** @param o **/
	public static <T extends Integer> void info(T o) {
		validateAndExecute(o, SeverityEnum.INFO);
	}

	/** @param o **/
	public static <T extends Integer> void warn(T o) {
		validateAndExecute(o, SeverityEnum.WARN);
	}

	/** @param o **/
	public static <T extends Integer> void error(T o) {
		validateAndExecute(o, SeverityEnum.ERROR);
	}

	/** @param o **/
	public static <T extends Integer> void debug(T o) {
		validateAndExecute(o, SeverityEnum.DEBUG);
	}

	/** @param o **/
	public static void info(int o) {
		validateAndExecute(o, SeverityEnum.INFO);
	}

	/**
	 * @param o
	 * */
	public static void warn(int o) {
		validateAndExecute(o, SeverityEnum.WARN);
	}

	/** @param o **/
	public static void error(int o) {
		validateAndExecute(o, SeverityEnum.ERROR);
	}

	/** @param o **/
	public static void debug(int o) {
		validateAndExecute(o, SeverityEnum.DEBUG);
	}

	/** @param o **/
	public static <T extends Long> void info(T o) {
		validateAndExecute(o, SeverityEnum.INFO);
	}

	/** @param o**/
	public static <T extends Long> void warn(T o) {
		validateAndExecute(o, SeverityEnum.WARN);
	}

	/** @param o **/
	public static <T extends Long> void error(T o) {
		validateAndExecute(o, SeverityEnum.ERROR);
	}

	/** @param o **/
	public static <T extends Long> void debug(T o) {
		validateAndExecute(o, SeverityEnum.DEBUG);
	}

	/** @param o **/
	public static void info(long o) {
		validateAndExecute(o, SeverityEnum.INFO);
	}

	/** @param o **/
	public static void warn(long o) {
		validateAndExecute(o, SeverityEnum.WARN);
	}

	/** @param o * */
	public static void error(long o) {
		validateAndExecute(o, SeverityEnum.ERROR);
	}

	/** @param o * */
	public static void debug(long o) {
		validateAndExecute(o, SeverityEnum.DEBUG);
	}

	/** @param o * */
	public static <T extends Boolean> void info(T o) {
		validateAndExecute(o, SeverityEnum.INFO);
	}

	/** @param o * */
	public static <T extends Boolean> void warn(T o) {
		validateAndExecute(o, SeverityEnum.WARN);
	}

	/** @param o * */
	public static <T extends Boolean> void error(T o) {
		validateAndExecute(o, SeverityEnum.ERROR);
	}

	/** @param o * */
	public static <T extends Boolean> void debug(T o) {
		validateAndExecute(o, SeverityEnum.DEBUG);
	}

	/** @param o * */
	public static <T extends Double> void info(T o) {
		validateAndExecute(o, SeverityEnum.INFO);
	}

	/** @param o * */
	public static <T extends Double> void warn(T o) {
		validateAndExecute(o, SeverityEnum.WARN);
	}

	/** @param o * */
	public static <T extends Double> void error(T o) {
		validateAndExecute(o, SeverityEnum.ERROR);
	}

	/** @param o * */
	public static <T extends Double> void debug(T o) {
		validateAndExecute(o, SeverityEnum.DEBUG);
	}

	/** @param o * */
	public static void info(boolean o) {
		validateAndExecute(o, SeverityEnum.INFO);
	}

	/** @param o * */
	public static void warn(boolean o) {
		validateAndExecute(o, SeverityEnum.WARN);
	}

	/** @param o * */
	public static void error(boolean o) {
		validateAndExecute(o, SeverityEnum.ERROR);
	}

	/** @param o * */
	public static void debug(boolean o) {
		validateAndExecute(o, SeverityEnum.DEBUG);
	}

	/**@param StackTraceElement @return String*/
	public static String getMensagem(StackTraceElement[] StackTraceElement) {
		String title = "\n\n\n\n   ##### >>>> " + StackTraceElement.getClass().getCanonicalName() + " ##### <<<<    ";
		StringBuilder stackTrace = new StringBuilder();
		for (StackTraceElement aStack : StackTraceElement) {
			if (isStakSnrPackage(aStack)) {
				stackTrace.append("       ERROR: (").append(aStack.toString()).append(")\n");
			}
		}
		return title + STACK + (!stackTrace.toString().isEmpty() ? stackTrace.toString() : "Nao foi lancada por um pacote do sistema");
	}
}
