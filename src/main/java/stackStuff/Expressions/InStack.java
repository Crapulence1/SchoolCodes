package stackStuff.Expressions;

public class InStack {
        private int top = -1;
        private String[] num;
         public InStack(int size) {
            this.num = new String[size / 2 + 1];
        }
        public void push(String a) {
            top++;
            num[top] = a;
        }
        public String pop() {
            if (isEmpty()) {
                throw new IllegalStateException("Stack underflow");
            }
            return num[top--];
        }
        public boolean isEmpty() {
            return top == -1;
        }
        public String peek() {
            try {
                return num[top];
            }
            catch (IndexOutOfBoundsException _){
                return null;
            }
        }
        public int length(){
            return top;
        }
        public void print() {
            for (String String : num){
                System.out.print(String + " ");
            }
            System.out.println();
        }
    }

