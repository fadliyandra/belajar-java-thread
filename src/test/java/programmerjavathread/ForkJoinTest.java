//package programmerjavathread;
//
//import org.junit.jupiter.api.Test;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.concurrent.*;
//import java.util.stream.Collectors;
//import java.util.stream.IntStream;
//
//public class ForkJoinTest {
//    @Test
//    void create() {
//        var forkJoinPool1 = ForkJoinPool.commonPool();
//        var forkJoinPool2 = new ForkJoinPool(5);
//
//        var excecutor1 = Executors.newWorkStealingPool();
//        var executors2 = Executors.newWorkStealingPool(5);
//
//
//    }
//
//    @Test
//    void recursiveAction() throws InterruptedException {
//        var pool= ForkJoinPool.commonPool();
//        List<Integer> integers = IntStream.range(0,1000).boxed().collect(Collectors.toList());
//
//        var task = new SimpleForkJoinTask(integers);
//        pool.execute(task);
//
//        pool.shutdown();
//        pool.awaitTermination(1, TimeUnit.DAYS);
//    }
//
//    @Test
//    void recursiveTask() throws ExecutionException, InterruptedException {
//        var pool= ForkJoinPool.commonPool();
//        List<Integer> integers = IntStream.range(0,1000).boxed().collect(Collectors.toList());
//
//        var task = new TotalRecursiveTask(integers);
//        ForkJoinTask<Long> submit = pool.submit(task);
//
//        Long along = submit.get();
//        System.out.println(along);
//
//        pool.shutdown();
//        pool.awaitTermination(1, TimeUnit.DAYS);
//
//    }
//
//    public static class SimpleForkJoinTask extends RecursiveAction{
//        private List<Integer> intgers;
//
//        public SimpleForkJoinTask(List<Integer> integers){
//            this.intgers = integers;
//
//        }
//
//        @Override
//        protected void compute() {
//            if(intgers.size()<= 10){
//                //eksekusi
//                doExecute();
//            }else{
//                //fork
//                forkCompute();
//
//            }
//
//        }
//
//        private void forkCompute() {
//            List<Integer> integers1 = this.intgers.subList(0, this.intgers.size()/2);
//            List<Integer> integers2 = this.intgers.subList(this.intgers.size()/2, this.intgers.size());
//
//            SimpleForkJoinTask task1 = new SimpleForkJoinTask(integers1);
//            SimpleForkJoinTask task2 = new SimpleForkJoinTask(integers2);
//
//
//            ForkJoinTask.invokeAll(task1,task2);
//
//        }
//
//        private void doExecute() {
//            intgers.forEach(integer -> System.out.println(Thread.currentThread().getName() + ":" + integer));
//
//        }
//    }
//
//    public static class TotalRecursiveTask extends RecursiveTask<Long>{
//
//        private List<Integer> integers;
//        private ArrayList<Object> intgers;
//
//        public TotalRecursiveTask(List<Integer> integers){
//            this.integers =integers;
//        }
//
//        @Override
//        protected Long compute() {
//            if(integers.size() <= 10){
//                return doCompute();
//            }else {
//                return forkCompute();
//            }
//        }
//
////        private Long forkCompute() {
//////          //  List<Integer> integers1 = this.intgers.subList(0, this.intgers.size()/2);
//////            //List<Integer> integers2 = this.intgers.subList(this.intgers.size()/2, this.intgers.size());
//////
//////            TotalRecursiveTask task1 = new TotalRecursiveTask(integers1);
//////            TotalRecursiveTask task2 = new TotalRecursiveTask(integers2);
//////
//////            ForkJoinTask.invokeAll(task1,task2);
//////            return task1.join() + task2.join();
////
////        };
//
//        private Long doCompute() {
//            return integers.stream().mapToLong(value -> value).peek(value -> {
//                System.out.println(Thread.currentThread().getName()+ " : " + value);
//            }).sum();
//        }
//    }
//}
