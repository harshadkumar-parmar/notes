// Step 1: Create the Handler Interface
interface Handler {
    void setNextHandler(Handler handler);
    void handleRequest(String request);
}

// Step 2: Create Abstract Handler Class
abstract class AbstractHandler implements Handler {
    protected Handler nextHandler;

    @Override
    public void setNextHandler(Handler handler) {
        this.nextHandler = handler;
    }

    @Override
    public void handleRequest(String request) {
        if (nextHandler != null) {
            nextHandler.handleRequest(request);
        }
    }
}

// Step 3: Create Concrete Handlers
class ConcreteHandlerA extends AbstractHandler {
    @Override
    public void handleRequest(String request) {
        System.out.println("-----");
        if (request.equals("A")) {
            System.out.println("Handler A processed the request.");
        } else {
            super.handleRequest(request);
        }
    }
}

class ConcreteHandlerB extends AbstractHandler {
    @Override
    public void handleRequest(String request) {
        System.out.println("======");
        if (request.equals("B")) {
            System.out.println("Handler B processed the request.");
        } else {
            super.handleRequest(request);
        }
    }
}

class ConcreteHandlerC extends AbstractHandler {
    @Override
    public void handleRequest(String request) {
        if (request.equals("C")) {
            System.out.println("Handler C processed the request.");
        } else {
            super.handleRequest(request);
        }
    }
}

// Step 4: Set Up the Chain and Handle the Request
public class Chain {
    public static void main(String[] args) {
        // Create handlers
        Handler handlerA = new ConcreteHandlerA();
        Handler handlerB = new ConcreteHandlerB();
        Handler c = new ConcreteHandlerC();

        // Set up the chain
        handlerA.setNextHandler(handlerB);
        handlerB.setNextHandler(c);

        // Handle requests
        handlerA.handleRequest("A");
        handlerA.handleRequest("B");
        handlerA.handleRequest("C");
    }
}


// output

// -----A
// Handler A processed the request.
// -----A
// ======B
// Handler B processed the request.
// -----A
// ======B
// Handler C processed the request.

