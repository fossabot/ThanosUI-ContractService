package com.github.abigail830.ThanosContractService.appl;

import com.github.abigail830.ThanosContractService.exception.BizException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.Socket;

@Service
@Slf4j
public class UtilApplService {

    public String sendTcpMsg(String host, Integer port, String request){
        try {
            final String result = raiseRequest(host, port, request);
            log.info(result);
            return result;
        } catch (IOException e) {
            throw new BizException("Exception during raise TCP request", e);
        }

    }

    private String raiseRequest(String host, Integer port, String request) throws IOException {
        Socket socket = new Socket(host, port);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        bw.write(request);
        bw.flush();
        socket.shutdownOutput();
        //read response
        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String response = br.readLine();
        log.info("ProviderMain responsed：" + response);

        socket.close();

        return response;
    }
}
