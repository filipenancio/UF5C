// EPOS First Thread Initializer

#include <utility/heap.h>
#include <system/kmalloc.h>
#include <thread.h>

extern "C" { void __epos_app_entry(); }

__BEGIN_SYS

class Init_First
{
private:
    typedef CPU::Log_Addr Log_Addr;

public:
    Init_First() {

        db<Init>(TRC) << "Init_First()" << endl;

        if(!Traits<System>::multithread) {
            CPU::int_enable();
            return;
        }

        db<Init>(INF) << "Initializing the first thread: " << endl;
        // If EPOS is not a kernel, then adjust the application entry point to __epos_app_entry,
        // which will directly call main(). In this case, _init will have already been called,
        // before Init_Application, to construct main()'s global objects.
        Thread::_running = new (kmalloc(sizeof(Thread))) Thread(Thread::Configuration(Thread::RUNNING, Thread::NORMAL), reinterpret_cast<int (*)()>(__epos_app_entry));
        db<Init>(INF) << "done!" << endl;


        // Exe3: fix idle thread
        // Neste ponto foi criada uma thread idle, utilizada para tratar os momentos
        // em que o SO espera por threads na fila de pronto e/ou não tenha outras threads para rodar
        db<Init>(INF) << "Initializing the idle thread: " << endl;
        Thread::init_idle();
        db<Init>(INF) << "done!" << endl;

        db<Init>(INF) << "INIT ends here!" << endl;

        This_Thread::not_booting();

        //Religando timer
        if(Thread::preemptive){
          Thread::_timer->enable();
        }

        Thread::running()->_context->load();
    }
};

// Global object "init_first" must be constructed last in the context of the
// OS, for it activates the first application thread (usually main())
Init_First init_first;

__END_SYS
