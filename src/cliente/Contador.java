package cliente;

import java.net.InetAddress;
import java.util.LinkedList;

import gerenciador.Usuario;

/**
 * trata participantes da lista Multicast
 * 
 * @author
 */
public class Contador {
	
	private LinkedList<Usuario> participantes;
        private boolean novoParticipante;
        
        public Contador(){
		participantes = new LinkedList<Usuario>();
	}

        public LinkedList<Usuario> getParticipantes() {
            return participantes;
        }

        public boolean isNovoParticipante() {
            return novoParticipante;
        }
	
        /**
         * imprime no console IDE todos participantes
         */
	public void exibirLista(){
            for (Usuario us : participantes) {
                System.out.println("\n "+us.getNome()+"\n tempo "+ us.getTempo());
            }
	}
        /**
         * Concatena usuarios ONLINE
         * @return concatencao de usuarios online
         */
        public String imprimirParticipantes(){
            String usuarios = "\n########-- Usuarios ONLINE --##########\n";
            for (Usuario usuario : participantes) {
                usuarios = usuarios + "\nONLINE " + usuario.getNome()+ ":" 
                                            + usuario.getIp() + ":"
                                            + usuario.getPorta()
                                            + "\n";
                                    
            }
            return usuarios;
        }
        /**
         *adiciona novos usuarios a lista de participantes 
         *ou zeraTempo() usuario jah existente 
         *
         * @param nome nome de usuario
         * @param address endereco usuario
         * @param port porta usuario
         */
	public void adicionarParticipante(String nome, InetAddress address, int port){
		final int pos = userExistente(address);
		if(pos == -1){
			Usuario u = new Usuario(nome,address.getHostAddress(),port);
			participantes.add(u);
			novoParticipante = true;
		}else{
			participantes.get(pos).zerarTempo();
                        novoParticipante = false;
		}
		
	}
        /**
         * Verifica se existe usuario com mesmo endereco IP
         * na lista de participante
         * 
         * @param address endereco
         * @return posicao de usuario existente, 
         * caso retorno -1 usuario inexistente
         */
        public int userExistente(InetAddress address){
            int i = 0;
            for(Usuario us : participantes){
                if(us.getIp().equals(address.getHostAddress())){		
                    return i;
                }
                i++;
            }
            return -1;
	}

        /**
         * remove usuario da lista de participantes caso
         * seu tempo tenha passado do limite
         */
        public void verificarTempo() {
            for (Usuario us : participantes) {
                if(us.getTempo() > 10){
                    participantes.remove(us);
                }
            }
	}
        
        /**
         * removo participante pelo endereco IP
         * @param address 
         */
        public void removerParticipante(InetAddress address){
            for (Usuario us : participantes) {
                if(us.getIp().equals(address.getHostAddress())){		
                    participantes.remove(us);
                }
            }
        }
}
