package br.com.control;

import br.com.model.ArquivoModel;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JOptionPane;

public class ArquivoControl {

    final private File caminhoArquivo;

    public ArquivoControl(String caminhoArquivo) {
        this.caminhoArquivo = new File(caminhoArquivo);
    }

    public ArquivoModel lerArquivo() {
        ArquivoModel arquivoModel = new ArquivoModel();
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(caminhoArquivo));
            br.readLine();
            while (br.ready()) {
                String linha = br.readLine();
                String[] dados = linha.split(";");
                if (dados.length > 3) {
                    JOptionPane.showMessageDialog(null, "O arquivo já foi editado ou não foi gerado pelo programa número 1");
                    break;
                }
                arquivoModel.setNome(dados[0]);
                arquivoModel.setTelefone(dados[1]);
                arquivoModel.setCpf(dados[2]);
            }
            br.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return arquivoModel;
    }

    public void escreverArquivo(ArquivoModel arquivoModel) throws IOException {

        BufferedWriter writer = new BufferedWriter(new FileWriter(caminhoArquivo.getAbsolutePath().
                substring(0,caminhoArquivo.getAbsolutePath().length()-4) + " Novo"+ caminhoArquivo.getAbsolutePath().
                substring(caminhoArquivo.getAbsolutePath().length()-4,caminhoArquivo.getAbsolutePath().length())));
        BufferedReader reader = new BufferedReader(new FileReader(caminhoArquivo));
        String tituloNovo = ";empresa;profissao;endereco";
        String palavraNova = ";" + arquivoModel.getEmpresa() + ";" + arquivoModel.getProfissao()
                + ";" + arquivoModel.getEndereco();
        String linha;
        linha = reader.readLine();
        linha = linha.replace(linha, linha.concat(tituloNovo));
        writer.write(linha + "\n");
        while ((linha = reader.readLine()) != null) {
            if (linha.contains(arquivoModel.getNome())) {
                linha = linha.replace(linha, linha.concat(palavraNova));
            }
            writer.write(linha + "\n");
        }

        writer.close();
        reader.close();
//
//        new File(caminhoArquivo.getAbsolutePath()).delete();
//        new File(caminhoArquivo.getAbsolutePath() + "bkp").renameTo(new File(caminhoArquivo.getAbsolutePath()));
    }

}
