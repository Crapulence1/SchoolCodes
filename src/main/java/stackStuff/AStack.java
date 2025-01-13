package stackStuff;

public class AStack {
        private int top = -1;
        private Object[] num;
         public AStack(int size) {
            this.num = new Object[size / 2 + 1];
        }
        public void push(Object a) {
            top++;
            num[top] = a;
        }
        public Object pop() {
            if (isEmpty()) {
                throw new IllegalStateException("Stack underflow");
            }
            return num[top--];
        }
        public boolean isEmpty() {
            return top == -1;
        }
        public Object peek() {
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
            for (Object Object : num){
                System.out.print(Object + " ");
            }
            System.out.println();
        }
    }

