package mini_games.StressEat.src.stresseat;

public class FoodDrop extends Thread{
        //Food drops - fix this
        public boolean movement(FallingItem food){
            for(int i = -1; i <= 400; i++){
                food.update(100);
            }
            return true;
        }

        @Override
        public void run(){
            double X = Math.floor((Math.random() * 601));
            int speed = (int)Math.ceil(Math.random() * 3);
            FallingItem food = new FallingItem(X, speed);
            movement(food);
        }
}
