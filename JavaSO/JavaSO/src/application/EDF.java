package application;

import java.io.IOException;
import java.util.*;

public class EDF extends MetodosEscalonamento{

	public EDF(ArrayList<Processo> lista, int q, int s) {
		super(lista, q, s);
	}
		
	int buscarProcesso(){
        int index = 0;
        for(int i = 1; i < getMain().size(); i++) {
            if(getMain().get(i).getCheg() + getMain().get(i).getDead() < 
                    getMain().get(index).getCheg() + getMain().get(index).getDead()) {
            	index = i;
            }
        }
        return index;
    }
	
	public void inserir(int t) {
		for(int i = 0; i < getLista().size(); i++)
			if(getLista().get(i).getCheg() <= t){
				getMain().add(getLista().get(i));
				getLista().remove((getLista().get(0 + i--)));
			}
	}
	
	public void executar() throws IOException {
		int quant = getLista().size();
		int tempo = 0;
		while(getFinalizados().size() < quant){
			inserir(tempo);
			if(getMain().isEmpty()==false){
				int proximoProcesso = buscarProcesso();

				Processo p = getMain().get(proximoProcesso);
				getMain().remove(0 + proximoProcesso);

				int runtime = (p.getExec() <= getQuantum()) ? p.getExec() - 1 : getQuantum() + getSobrecarga() - 1; //Preemptivo

				for(int i = 0; i < getLista().size(); i++){
					if(getLista().get(i).getCheg() <= tempo + runtime){
						getMain().add(getLista().get(i));
						getLista().remove(0 + (i--));
					}
				}
				if(p.getExec() <= getQuantum()){
					int tempoRestante = p.getExec();
					p.setExec(0);
			
					p.inserirExecucao(tempo, tempo + tempoRestante - 1);
					getFinalizados().add(p);

					tempo += tempoRestante - 1;
				}
				else{
					p.setExec(p.getExec() - getQuantum());
					
					p.inserirExecucao(tempo, tempo + getQuantum() - 1);
					p.inserirExecucao(tempo + getQuantum(), tempo + getQuantum() + getSobrecarga() - 1);
					getMain().add(p);
					tempo += getQuantum() + getSobrecarga() - 1;
				}
			}
			tempo++;
		}
		Collections.sort(getFinalizados());
				
		for(Processo p : getFinalizados()) {
			String imprimir = p.Gantt();
			getLinha().add(imprimir);
		}

		new Thread() {
            @Override
            public void run() {
                javafx.application.Application.launch(Executor.class);
            }
        }.start();
        Executor startUpTest = Executor.waitForStartUpTest();
        startUpTest.setList(getFinalizados());
    }
}