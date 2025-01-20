package stackStuff.Expressions;

public class PostStack {
        private int top = -1;
        private double[] num;
         public PostStack(int size) {
            this.num = new double[size / 2 + 1];
        }
        public void push(double a) {
            top++;
            num[top] = a;
        }
        public double pop() {
            if (isEmpty()) {
                throw new IllegalStateException("Stack underflow");
            }
            return num[top--];
        }
        public boolean isEmpty() {
            return top == -1;
        }
        public double peek() {
            return num[top];
        }


    }

