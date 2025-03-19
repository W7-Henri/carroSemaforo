package controller;

import java.util.concurrent.Semaphore;

class Carro extends Thread {
    private String sentido;
    private Semaphore semaforo;

    public Carro(String sentido, Semaphore semaforo) {
        this.sentido = sentido;
        this.semaforo = semaforo;
    }

    @Override
    public void run() {
        try {
            semaforo.acquire(); // Garante que apenas um carro passe por vez
            System.out.println("Carro " + Thread.currentThread().getId() + " passando no sentido: " + sentido);
            Thread.sleep(1000); // Simula o tempo de travessia
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaforo.release();
        }
    }
}

public class Cruzamento {
    public static void main(String[] args) {
        Semaphore semaforo = new Semaphore(1); // Apenas um carro por vez

        String[] sentidos = {"Norte -> Sul", "Sul -> Norte", "Oeste -> Leste", "Leste -> Oeste"};
        
        for (String sentido : sentidos) {
            new Carro(sentido, semaforo).start();
        }
    }
}
